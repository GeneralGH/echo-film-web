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

export const getRecommendFilmList = ()=> {
	return request({
		url:'/api/film/getRecommendFilms',
		method: 'get'
	})
};

export const getRecommendListByType = (data)=> {
	return request({
		url:'/api/film/getRecommendListByType',
		method: 'post',
		data
	})
};

export const addWatch = (id)=> {
	return request({
		url:'/api/film/addWatch/' + id,
		method: 'get'
	})
};