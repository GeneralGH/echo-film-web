<template>
	<view style="padding: 0 20rpx;">
		<view class="search-area">
			<u-search v-model="listQuery.name" :clearabled="true" @search="search" @custom="search"></u-search>
		</view>
		<scroll-view scroll-y="true" :style="scrollHeightStr" @scrolltolower="getList">
			<FilmList :filmList="list" :col="1" />
		</scroll-view>
	</view>
</template>

<script>
	import FilmList from '@/components/filmList.vue'
	import {
		getFilmList
	} from '@/common/js/film.js'
	export default {
		components: { FilmList },
		data() {
			return {
				scrollHeightStr: '',
				list: [],
				listQuery: {
					pageNum: 1,
					pageSize: 10,
					name: ''
				}
			};
		},
		methods: {
			getList() {
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
			
			search() {
				this.listQuery.pageNum = 1
				this.list = []
				this.getList()
			},
			
			async setScrollHeight() {
				let that = this
				let window = uni.getWindowInfo()
				const query = uni.createSelectorQuery().in(this);
				await query.select(".search-area")
					.boundingClientRect((data) => {
						that.scrollHeightStr = `height: ${window.windowHeight - data.height}px;`
					})
					.exec();
			}
		},
		onLoad() {
			this.setScrollHeight()
		}
	}
</script>

<style lang="scss" scoped>
.search-area {
	padding: 30rpx 0;
}
</style>
