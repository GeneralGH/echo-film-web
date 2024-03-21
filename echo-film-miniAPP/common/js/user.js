import Request from '@/utils/require.js'; 
let request = new Request().http;
// 登录
export const login = (data)=> {
	return request({
		url:'/api/user/login',
		method: 'post',
		data
	})
};

// 修改
export const updateUser = (data)=> {
	return request({
		url:'/api/user/updateUser',
		method: 'put',
		data
	})
};