<template>
	<view>
		<HomeHeader @typeChange="typeChange" id="home-header" />
		<scroll-view scroll-y="true" :style="scrollHeightStr" @scrolltolower="scrollToLower">
			<view class="swiper-area">
				<u-swiper @click="swiperJump" :list="recommendList" keyName="coverUrl" showTitle indicator circular :radius="10" height="350rpx"></u-swiper>
			</view>
			<FilmList :filmList="list" :col="2" />
		</scroll-view>
	</view>
</template>

<script>
	import HomeHeader from '@/components/homeHeader.vue';
	import FilmList from '@/components/filmList.vue'
	import {
		getFilmList,
		getRecommendFilmList
	} from '@/common/js/film.js'

	export default {
		components: {
			HomeHeader,
			FilmList
		},
		data() {
			return {
				listQuery: {
					pageNum: 1,
					pageSize: 10,
					name: '',
					filmType: ''
				},
				list: [],
				recommendList: [],
				scrollHeightStr: ''
			};
		},

		methods: {
			typeChange(e) {
				this.listQuery.filmType = e
				this.listQuery.pageNum = 1
				this.list = []
				this.getList()
			},

			getList() {
				this.listQuery.filmType = this.listQuery.filmType ? this.listQuery.filmType : ''
				getFilmList(this.listQuery)
					.then(res => {
						if (this.listQuery.pageNum > res.data.totalPage) {
							return
						}
						this.list = this.list.length ? this.list.concat(res.data.list.map((item) => {
							return {
								...item,
								coverUrl: JSON.parse(item.cover).url
							}
						}))
						:
						res.data.list.map((item) => {
							return {
								...item,
								coverUrl: JSON.parse(item.cover).url
							}
						})
					})
			},

			getRecommendFilms() {
				getRecommendFilmList()
					.then(res => {
						this.recommendList = res.data.map((item) => {
							return {
								...item,
								coverUrl: JSON.parse(item.cover).url,
								title: item.name
							}
						})
					})
			},
			
			swiperJump(index) {
				this.$store.commit('setFilm', this.recommendList[index])
				uni.navigateTo({
					url: '/pages/film/detail'
				})
			},
			
			scrollToLower() {
				this.listQuery.pageNum += 1
				this.getList()
			},

			async setScrollHeight() {
				let that = this
				let window = uni.getWindowInfo()
				const query = uni.createSelectorQuery().in(this);
				await query.select("#home-header")
					.boundingClientRect((data) => {
						that.scrollHeightStr = `height: ${window.windowHeight - data.height}px;`
					})
					.exec();
			}
		},

		onLoad() {
			this.setScrollHeight()
			this.getList()
			this.getRecommendFilms()
		}
	}
</script>

<style lang="scss">
	.swiper-area {
		padding: 0 20rpx;
		box-sizing: border-box;
		margin: 30rpx 0 50rpx 0;
	}
</style>