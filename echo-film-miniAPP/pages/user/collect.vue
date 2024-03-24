<template>
	<view>
		<scroll-view scroll-y="true" style="height: 100vh;">
			<FilmList :filmList="list" :col="1" />
		</scroll-view>
	</view>
</template>

<script>
	import FilmList from '@/components/filmList.vue'
	import {
		getFilmsListByFilmIds
	} from '@/common/js/film.js'
	import {
		getUserCollect
	} from '@/common/js/collect.js'
	export default {
		components: {
			FilmList
		},
		data() {
			return {
				list: []
			};
		},

		methods: {
			getCollect() {
				let userId = uni.getStorageSync('userInfo').userId
				getUserCollect(userId)
					.then(res => {
						if (res.data.length == 0) {
							return
						}
						let collectFilmIds = res.data.map((item) => {
							return item.filmId
						})
						this.getFilms(collectFilmIds)
					})
			},

			getFilms(collectFilmIds) {
				getFilmsListByFilmIds(collectFilmIds)
					.then(res => {
						this.list = res.data.map((item) => {
							return {
								...item,
								coverUrl: JSON.parse(item.cover).url
							}
						})
					})
			}
		},

		onLoad() {
			this.getCollect()
		}
	}
</script>

<style lang="scss">

</style>