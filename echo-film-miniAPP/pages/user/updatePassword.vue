<template>
	<view style="background-color: white; height: 100vh;">
		<view class="login-area">
			<u--form labelPosition="left" >
				<u-form-item label="密码" :borderBottom="false">
					<u--input v-model="userPassword" shape="circle"></u--input>
				</u-form-item>
			</u--form>
			<view class="log-btn">
				<u-button @click="submit" shape="circle" color="linear-gradient(to right, #1FA2FF 0%,  #1FA2FF 51%, #12D8FA  100%);">提交</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	import { updateUser } from '@/common/js/user.js'
	export default {
		data() {
			return {
				userPassword: '',
				user: uni.getStorageSync('userInfo')
			};
		},

		methods: {
			submit() {
				if (this.userPassword) {
					this.user.userPassword = this.userPassword
					updateUser(this.user)
					.then(res => {
						uni.removeStorageSync('userInfo')
						setTimeout(() => {
							uni.switchTab({
								url: '/pages/tabbar/index'
							})
						}, 2000)
						uni.showToast({
							icon: 'success',
							title: '修改成功'
						})
					})
				} else {
					uni.showToast({
						icon: 'none',
						title: '请填写密码'
					})
				}
			}
		}
	}
</script>

<style lang="scss">
	.login-area {
		padding: 0 80rpx;
		margin-top: 60rpx;
	}
	
	.log-btn {
		margin-top: 60rpx;
	}
</style>