<template>
	<view>
		<u-grid :border="false" :col="col">
			<u-grid-item v-for="(item,index) in filmList" :key="item.filmId" @click="toDetail(item)">
				<view class="film-item" :style="col == 2 ? 'height: 300rpx;' : 'height: 450rpx;'">
					<image :src="item.coverUrl" mode="aspectFill" :style="col == 2 ? 'height: 200rpx;' : 'height: 320rpx;'"></image>
					<view class="film-title">{{ item.name }}</view>
					<view class="film-subTitle">
						<view class="film-time">{{ item.releaseTime.split("T")[0] }}</view>
						<view class="film-watchNumber">播放量：{{ item.watchNumber || 0 }}</view>
					</view>
				</view>
			</u-grid-item>
		</u-grid>
	</view>
</template>

<script>
	export default {
		name: "filmList",
		data() {
			return {

			};
		},
		props: {
			filmList: { type: Array, default: () => [] },
			col: { type: Number, default: 1 }
		},
		methods: {
			toDetail(item) {
				this.$store.commit('setFilm', item)
				uni.navigateTo({
					url: '/pages/film/detail'
				})
			}
		}
	}
</script>

<style lang="scss">
.film-item {
	width: 90%;
	margin-bottom: 30rpx;
	background-color: white;
	
	image {
		width: 100%;
		border-radius: 20rpx;
	}
	
	.film-title {
		font-weight: bold;
		margin-top: 20rpx;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		word-break: break-all;
	}
	
	.film-subTitle {
		display: flex;
		align-items: center;
		justify-content: space-between;
		margin-top: 15rpx;
		font-size: 24rpx;
	}
	
	.film-time {
		color: #9e9e9e;
	}
	
	.film-watchNumber {
	}
}
</style>