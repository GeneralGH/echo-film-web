import Request from '@/utils/require.js'; 
let request = new Request().http;
// 
export const getFilmList = (data)=> {
	return request({
		url:'/api/film/getFilmList',
		method: 'post',
		data
	})
};