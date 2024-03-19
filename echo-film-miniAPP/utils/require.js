import operate from './operate.js'
export default class Request {
	http(param) {
		// 请求参数
		var url = param.url,
			method = param.method,
			// header = param.header,
			// token = param.token || "",
			data = param.data || {},
			// 新加
			header = {
				'Content-Type':param.header?'application/x-www-form-urlencoded':'application/json;charset=utf-8',
			},
			headerT = {
				'Content-Type':param.header?'application/x-www-form-urlencoded':'application/json;charset=utf-8',
			},

			hideLoading = param.hideLoading || false;

		//拼接完整请求地址
		var requestUrl = operate.api() + url;

		//请求方式:GET或POST(POST需配置
		if (method) {
			method = method.toUpperCase(); //小写改为大写
			// if (method == "POST") {
			//     header = {
			//         'content-type': "application/x-www-form-urlencoded"
			//     };
			// } else {
			//     header = {
			//         'content-type': "application/json"
			//     };
			// }
		}

		//加载圈
		if (!hideLoading) {
			uni.showLoading({
				title: '加载中...'
			});
		}

		// 返回promise
		return new Promise((resolve, reject) => {
			// 请求
			uni.request({
				url: requestUrl,
				data: data,
				method: method,
				header: header.token==''?headerT:header,
				success: (res) => {
					let result = res.data;
					let result_code = res.data.code ? res.data.code : res.data;
					// console.log(uni.getStorageSync('userInfo').token)
					// 错误代码
					let alert = false;
					let alert_login = false;
					switch (result_code) {
						case 501:
							alert = true;
							alert_login = false;
							result_code = res.data.msg//输入错误或者参数错误，文件上传时
							break;
						case 502:
							alert = true;
							alert_login = true;
							result_code = '登录已过期'
							uni.removeStorage({key: 'userInfo'})
							break;
						case 500:
							alert = true;
							alert_login = false;
							result_code = res.data.msg
							break;
						case 203:
							alert = true;
							result_code = '参数错误'
							break;
					}
					resolve(result)
				},
				//请求失败
				fail: (e) => {
					console.log(e,'接口请求失败')
					console.log(e)
					console.log(e)
					uni.showToast({
						title: e.data.msg,
						icon: 'none'
					});
					resolve(e.data.data);
				},
				//请求完成
				complete() {
					//隐藏加载
					if (!hideLoading) {
						uni.hideLoading();
					}
					resolve();
					return;
				}
			})
		})
	}
}
