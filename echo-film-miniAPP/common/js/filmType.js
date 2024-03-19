import Request from '@/utils/require.js'; 
let request = new Request().http;
// 
export const getFilmTypeList = ()=> {
	return request({
		url:'/api/filmtype/getFilmTypeList',
		method: 'get'
	})
};