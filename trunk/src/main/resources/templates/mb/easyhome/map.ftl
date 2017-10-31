<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>商场地图</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="assets/mui/css/mui.min.css">
		<link rel="stylesheet" href="css/master.css" />
		<!--photoswipe-->
		<link rel="stylesheet" href="assets/photoswipe/photoswipe.css" />
		<link rel="stylesheet" href="assets/photoswipe/default-skin/default-skin.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />

	</head>

	<body>
		<div class="mui-content">
			<!--轮播图片-->
			<#include "banner_div.ftl" />
			<!--内容-->
			<div class="contact_group map_group">	
			<h1 class="title"><i class="g_l_icon"></i>商场地图 <span class="text">点击图片放大</span></h1>	
			<div class="my-simple-gallery" itemscope >  
				
			    <figure itemscope >
			      <a href="img/map_0.png" itemprop="contentUrl" data-size="717x369">
			          <img class="img" src="img/map_0.png" itemprop="thumbnail" alt="Image description" />
			      </a>
			      <figcaption itemprop="caption description">1F</figcaption>
			    </figure>
			
			    <figure itemscope >
			      <a href="img/map_0.png" itemprop="contentUrl" data-size="717x369">
			          <img class="img" src="img/map_0.png" itemprop="thumbnail" alt="Image description" />
			      </a>
			      <figcaption itemprop="caption description">2F</figcaption>
			    </figure>
			
			    <figure itemscope >
			      <a href="img/map_0.png" itemprop="contentUrl" data-size="717x369">
			          <img class="img" src="img/map_0.png" itemprop="thumbnail" alt="Image description" />
			      </a>
			      <figcaption itemprop="caption description">3F</figcaption>
			    </figure>
			
			
			  </div>
				<!-- Root element of PhotoSwipe. Must have class pswp. -->
				<div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">

					<!-- Background of PhotoSwipe. 
				         It's a separate element, as animating opacity is faster than rgba(). -->
					<div class="pswp__bg"></div>

					<!-- Slides wrapper with overflow:hidden. -->
					<div class="pswp__scroll-wrap">

						<!-- Container that holds slides. PhotoSwipe keeps only 3 slides in DOM to save memory. -->
						<div class="pswp__container">
							<!-- don't modify these 3 pswp__item elements, data is added later on -->
							<div class="pswp__item"></div>
							<div class="pswp__item"></div>
							<div class="pswp__item"></div>
						</div>

						<!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
						<div class="pswp__ui pswp__ui--hidden">

							<div class="pswp__top-bar">

								<!--  Controls are self-explanatory. Order can be changed. -->

								<div class="pswp__counter"></div>

								<button class="pswp__button pswp__button--close" title="Close (Esc)"></button>

								<!--<button class="pswp__button pswp__button--share" title="Share"></button> -->

								<button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>

								<button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>

								<!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
								<!-- element will get class pswp__preloader--active when preloader is running -->
								<div class="pswp__preloader">
									<div class="pswp__preloader__icn">
										<div class="pswp__preloader__cut">
											<div class="pswp__preloader__donut"></div>
										</div>
									</div>
								</div>
							</div>

							<div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
								<div class="pswp__share-tooltip"></div>
							</div>

							<button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
				            </button>

							<button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
				            </button>

							<div class="pswp__caption">
								<div class="pswp__caption__center"></div>
							</div>

						</div>

					</div>

				</div>
			</div>

		</div>

		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav">
			<a class="mui-tab-item" type="0">
				<span class="mui-icon iconfont icon-shouye"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item mui-active" type="1">
				<span class="mui-icon mui-icon-location"></span>
				<span class="mui-tab-label">商场地图</span>
			</a>
			<a class="mui-tab-item" type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>
		</nav>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="assets/photoswipe/photoswipe.min.js"></script>
		<script type="text/javascript" src="assets/photoswipe/photoswipe-ui-default.min.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/map.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
		</script>

	</body>

</html>