<template>
	<view class="header-area">
		<view class="search-area" :style="searchPosition" @click="toSearch">
			搜索
		</view>
		<view class="tab-list">
			<u-tabs :list="list" @click="tabClick"
				:activeStyle="{
					color: '#fff',
					fontWeight: 'bold',
					transform: 'scale(1.5)'
					
				}"
				:inactiveStyle="{
					color: '#fff',
					transform: 'scale(1.1)'
				}"
			></u-tabs>
		</view>
	</view>
</template>

<script>
	import { getFilmTypeList } from '@/common/js/filmType.js'
	export default {
		name:"homeHeader",
		data() {
			return {
				// 获取菜单按钮（右上角胶囊按钮）的布局位置信息。坐标信息以屏幕左上角为原点。
				menuBottonRes: wx.getMenuButtonBoundingClientRect(),
				searchPosition: '',
				list: [],
				currentType: 0
			};
		},
		methods: {
			getTypeList() {
				getFilmTypeList()
				.then(res => {
					this.list = res.data
					this.list.unshift({ id: 0, name: '全部' })
				})
			},
			
			toSearch() {
				uni.navigateTo({
					url: '/pages/search/search'
				})
			},
			
			tabClick(e) {
				this.currentType = e.id
				this.$emit('typeChange', this.currentType)
			}
		},
		created() {
			this.searchPosition = `top: ${this.menuBottonRes.top}px; left: ${this.menuBottonRes.left / 2 - 70}px; height: ${this.menuBottonRes.height}px; line-height: ${this.menuBottonRes.height}px`
			this.getTypeList()
		}
	}
</script>

<style lang="scss">
.header-area {
	width: 100%;
	background-image: linear-gradient(to right, #1FA2FF 0%,  #1FA2FF 51%, #12D8FA  100%);
	height: 270rpx;
	position: relative;
	
	.search-area {
		position: absolute;
		width: 370rpx;
		background-color: #f1f2f3b8;
		border-radius: 20rpx;
		text-align: center;
		color: #707070;
	}
	
	.tab-list {
		position: absolute;
		bottom: 10rpx;
		padding: 0 20rpx;
	}
}
</style>