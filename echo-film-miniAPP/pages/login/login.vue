<template>
	<view style="background-color: white; height: 100vh;">
		<view class="login-area">
			<u--form labelPosition="left" :model="model1" :rules="rules" ref="uForm">
				<u-form-item label="账号" prop="userInfo.userAccount" :borderBottom="false" ref="item1">
					<u--input v-model="model1.userInfo.userAccount" shape="circle"></u--input>
				</u-form-item>
				<u-form-item label="密码" prop="userInfo.userPassword" :borderBottom="false" ref="item1">
					<u--input v-model="model1.userInfo.userPassword" shape="circle" type="password"></u--input>
				</u-form-item>
			</u--form>
			<view class="log-btn">
				<u-button @click="submit" shape="circle" color="linear-gradient(to right, #1FA2FF 0%,  #1FA2FF 51%, #12D8FA  100%);">登录</u-button>
			</view>
		</view>
	</view>
</template>

<script>
	import { login } from '@/common/js/user.js'
	export default {
		data() {
			return {
				model1: {
					userInfo: {
						userAccount: '',
						userPassword: '',
					},
				},
				rules: {
					'userInfo.userAccount': {
						type: 'string',
						required: true,
						message: '请填写账号',
						trigger: ['blur', 'change']
					},
					'userInfo.userPassword': {
						type: 'string',
						required: true,
						message: '请填写密码',
						trigger: ['blur', 'change']
					},
				}
			};
		},

		methods: {
			submit() {
				this.$refs.uForm.validate().then(res => {
					login(this.model1.userInfo)
					.then(res => {
						if (res.code == 0) {
							uni.$u.toast(res.msg)
							return
						}
						uni.setStorageSync('userInfo', res.data)
						uni.switchTab({
							url: '/pages/tabbar/index'
						})
					})
				}).catch(errors => {
					uni.$u.toast('请填写完整')
				})
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