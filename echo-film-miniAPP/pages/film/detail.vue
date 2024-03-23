<template>
	<view>
		<scroll-view scroll-y="true" style="height: 100vh;">
			<view class="video-area">
				<video :src="filmObj.filmUrl.url" controls></video>

				<view class="film-info-area">
					<u-read-more ref="uReadMore" :toggle="true" showHeight="100" textIndent="" closeText="展开详情">
						<view class="film-title"><text>影片名：</text> {{ filmObj.name }}</view>
						<u-line margin="30rpx 0"></u-line>
						<view class="film-title"><text>类型：</text> {{ filmObj.typeStr }}</view>
						<u-line margin="30rpx 0"></u-line>
						<view class="film-director"><text>导演：</text>{{ filmObj.director }}</view>
						<u-line margin="30rpx 0"></u-line>
						<view class="film-actor"><text>演员：</text>{{ filmObj.actor }}</view>
						<u-line margin="30rpx 0"></u-line>
						<view class="film-actor"><text>上映时间：</text>{{ filmObj.releaseTime }}</view>
						<u-line margin="30rpx 0"></u-line>
						<view class="film-actor"><text>播放量：</text>{{ filmObj.watchNumber || 0 }}</view>
						<u-line margin="30rpx 0"></u-line>
						<view class="film-info">
							<view style="margin-bottom: 20rpx; font-weight: bold;">简介：</view>
							<u-parse :content="filmObj.info" @ready="load"></u-parse>
						</view>
					</u-read-more>
				</view>

				<view class="btns">
					<u-button type="primary" plain
					:icon="isCollect ? 'star-fill' : 'star'"
					:text="isCollect ? '取消收藏' : '点击收藏'"
					shape="circle" @click="collect(filmObj.filmId)" ></u-button>
				</view>
				
				<u-divider
				        text="相关推荐"
				        textColor="#2979ff"
				        lineColor="#2979ff"
				></u-divider>
				
				<FilmList :filmList="recommendList" :col="2" />
			</view>
		</scroll-view>
	</view>
</template>

<script>
	import {
		getFilmTypeList
	} from '@/common/js/filmType.js'
	import { getUserCollect, addCollect, delCollect } from '@/common/js/collect.js'
	import { getRecommendListByType, addWatch } from '@/common/js/film.js'
	import FilmList from '@/components/filmList.vue'
	
	export default {
		components: { FilmList },
		data() {
			return {
				filmObj: {},
				filmTypeList: [],
				isCollect: false,
				currentCollectFilm: {},
				recommendList: []
			};
		},

		filters: {
		},

		methods: {
			collect(filmId) {
				let userId = uni.getStorageSync('userInfo').userId
				if (this.isCollect) {
					delCollect(this.currentCollectFilm.id)
					.then(res => {
						if (res.code == 200) {
							this.isCollect = false
						}
					})
				} else {
					addCollect({ filmId: filmId, userId: userId })
					.then(res => {
						if (res.code == 200) {
							this.currentCollectFilm = res.data
							this.isCollect = true
						}
					})
				}
			},
			
			load() {
				this.$refs.uReadMore.init();
			},

			async getFilmTypeList() {
				await getFilmTypeList()
					.then(res => {
						this.filmTypeList = res.data
					})
			},
			
			getCurrentFilmCollectSatus(filmId, userId) {
				getUserCollect(userId)
				.then(res => {
					this.currentCollectFilm = res.data.find((obj) => {
						return obj.filmId == this.filmObj.filmId
					})
					let collectFilmIds = res.data.map((item) => { return item.filmId })
					this.isCollect = collectFilmIds.includes(filmId)
				})
			},
			
			getRecommendList() {
				getRecommendListByType({ filmType: this.filmObj.typeIds })
				.then(res => {
					this.recommendList = res.data.filter(film => film.filmId !== this.filmObj.filmId)
					this.recommendList = this.recommendList.map((item) => { return { ...item, coverUrl: JSON.parse(item.cover).url } })
				})
			},
			
			initData() {
				let that = this
				this.filmObj = uni.$u.deepClone(this.$store.state.filmObj)
				this.filmObj.releaseTime = this.filmObj.releaseTime.split("T")[0]
				this.filmObj.typeStr = this.filmObj.typeIds.split(',').map(item => {
					const foundObj = that.filmTypeList.find(obj => obj.id === parseInt(item));
					return foundObj ? foundObj.name : "";
				}).join("/")
				this.filmObj.filmUrl = JSON.parse(this.filmObj.filmUrl)
				addWatch(this.filmObj.filmId)
			}
		},

		async onLoad() {
			let isLogin = uni.getStorageSync('userInfo') ? true : false
			if (!isLogin) {
				uni.showToast({
					title: '请先登录',
					icon: 'none'
				})
				setTimeout(() => {
					uni.redirectTo({
						url: '/pages/login/login'
					})
				}, 2000)
			} else {
				await this.getFilmTypeList()
				this.initData()
				this.$refs.uReadMore.init();
				this.getCurrentFilmCollectSatus(this.filmObj.filmId, uni.getStorageSync('userInfo').userId)
				this.getRecommendList()
			}
		},

		onUnload() {
			this.$store.commit('setFilm', {})
		}
	}
</script>

<style lang="scss">
	.video-area {
		padding-bottom: 100rpx;
		
		video {
			width: 100%;
			height: 600rpx;
		}

		.film-info-area {
			margin-top: 40rpx;
			padding: 0 20rpx;

			.film-title,
			.film-director,
			.film-actor {
				text {
					font-weight: bold;
					font-size: 32rpx;
				}
			}
			
			.film-info {
				word-break: break-all;
			}
		}
		
		.btns {
			width: 180rpx;
			margin: 50rpx 0;
			padding: 0 20rpx;
		}
	}
</style>