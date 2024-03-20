<template>
	<view>
		<u-grid :border="false" :col="col">
			<u-grid-item v-for="(item,index) in filmList" :key="item.filmId">
				<view class="film-item" :style="col == 2 ? 'height: 300rpx;' : 'height: 500rpx;'">
					<image :src="item.coverUrl" mode="aspectFill" :style="col == 2 ? 'height: 200rpx;' : 'height: 400rpx;'"></image>
					<view class="film-title">{{ item.name }}</view>
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
}
</style>