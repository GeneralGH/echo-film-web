import axios from 'axios'
import proxy from '@/config/host';
import { TOKEN_NAME } from '@/config/global';
const env = import.meta.env.MODE || 'development';
const API_HOST = proxy[env].API;
import uploadApi from '@/api/upload';
const uploadUrl = API_HOST + uploadApi.uploadFileUrl

export function uploadFilesToServer(fileName, files) {
    const formData = new FormData()
    formData.append(fileName, files)
    const headers = { 'Content-Type': 'multipart/form-data' }
    const request = axios.create({
        headers: headers
    })

    return request
        .post(uploadUrl, formData)
        .then((res) => {
            return Promise.resolve({ status: 'success', response: { data: res.data.data }})
        })
        .catch((error) => {
            return Promise.reject({ status: 'fail', error: '上传失败，请检查文件是否符合规范' })
        })
}