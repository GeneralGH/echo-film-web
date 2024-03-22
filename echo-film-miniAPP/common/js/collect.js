import Request from '@/utils/require.js'; 
let request = new Request().http;
// 
export const getUserCollect = (id)=> {
	return request({
		url:'/api/collect/getUserCollect/' + id,
		method: 'get'
	})
};

export const addCollect = (data)=> {
	return request({
		url:'/api/collect/addCollect',
		method: 'post',
		data
	})
};

export const delCollect = (id)=> {
	return request({
		url:'/api/collect/delCollect/' + id,
		method: 'delete'
	})
};