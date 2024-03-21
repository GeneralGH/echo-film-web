<template>
	<view style="padding: 30rpx 20rpx; background-color: #f5f5f5; height: 100vh;">
		<view class="user-info">
			<view class="info-left">
				<text v-if="!isLogin">请点击登录</text>
				<text v-if="isLogin">{{ userInfo.userName | sayHellow }}</text>
			</view>
			<view class="info-right">
				<u-button color="linear-gradient(to right, #FF512F 0%, #FF512F 51%, #F09819 100%)"
					:text="isLogin ? '退出登录' : '登录'" shape="circle" @click="toLogin"></u-button>
			</view>
		</view>

		<view class="user-setting">
			<view class="collect" @click="jump('/pages/user/collect')">
				收藏列表
			</view>
			<view class="updatePassword" @click="jump('/pages/user/updatePassword')">
				修改密码
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isLogin: 0,
				userInfo: ''
			};
		},
		filters: {
			sayHellow(e) {
				const now = new Date()
				const currentHour = now.getHours()
				let greeting = ""
				if (currentHour < 12) {
					greeting = "早上好！"
				} else if (currentHour < 18) {
					greeting = "下午好！"
				} else {
					greeting = "晚上好！"
				}
				return greeting + e
			}
		},
		methods: {
			toLogin() {
				if (this.isLogin) {
					uni.removeStorageSync('userInfo')
					uni.switchTab({
						url: '/pages/tabbar/index'
					})
				} else {
					uni.navigateTo({
						url: '/pages/login/login'
					})
				}
			},
			jump(url) {
				uni.navigateTo({
					url: url
				})
			}
		},
		onShow() {
			this.userInfo = uni.getStorageSync('userInfo')
			this.isLogin = this.userInfo ? 1 : 0
		}
	}
</script>

<style lang="scss">
	.user-info {
		height: 150rpx;
		background-color: white;
		border-radius: 15rpx;
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 0 20rpx;

		.info-left {
			width: 300rpx;
			font-weight: bold;
		}

		.info-right {
			width: 160rpx;
		}
	}

	.user-setting {
		/* padding: 0 20rpx; */

		view {
			margin-top: 25rpx;
			background-color: white;
			border-radius: 15rpx;
			padding: 30rpx 20rpx;
		}
	}
</style>