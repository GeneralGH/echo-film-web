export default {
	//接口
	api: function() {
	    let version = wx.getAccountInfoSync().miniProgram.envVersion;
	    switch (version) {
			case "develop": //开发预览版
				return "https://echofilm.lmtss.fun"
				break;
			case 'trial': //体验版
				return "https://echofilm.lmtss.fun"
				break;
			case 'release': //正式版				
				return "https://echofilm.lmtss.fun"
				// return "https://xcx.xzbdc.com/cpbApps/"
				// return "https://wxxz.xzbdc.com/cpbApps/"
				break;
			default: //未知,默认调用正式版
				return "https://echofilm.lmtss.fun"
				break;
		}
	}
}