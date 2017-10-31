<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>居然之家</title>
		<link rel="stylesheet" href="assets/swiper/swiper.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootsnav.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/menu_group.css" />
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="css/model.css" />
		<!--photoswipe-->
		<link rel="stylesheet" href="assets/photoswipe/photoswipe.css" />
		<link rel="stylesheet" href="assets/photoswipe/default-skin/default-skin.css" />
		<style>
			body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, code,
			form, fieldset, legend, input, textarea, p, blockquote, th, td, hr,
			button, article, aside, details, figcaption, figure, footer, header,
			hgroup, menu, nav, section {
				text-align: inherit;
			}
		</style>
	</head>

	<body>
		<!-- 页眉 -->
		<#include "head.ftl" />
		
		<div class="cont">
			<div class="container cont_group">
				<div class="cont_title_group">
					<div class="row g-border">
						<div class="col-md-8">
							<p class="title">
								<a href="index.html">首页</a>
							</p>
							<i class="icon_next"></i>
							<p class="title">
								<a>商场地图</a>
							</p>
						</div>
						<div class="col-md-4">
							<p class="pading">
							</p>
						</div>
					</div>

					<!--end-->
					<div class="map">
						<h1 class="title"><i class="g_l_icon"></i>商场地图 <span class="text">点击图片放大</span></h1>	
					</div>
				<div class="my-simple-gallery" itemscope>
					<figure itemscope>
						<a href="img/map/m1.jpg" itemprop="contentUrl" data-size="1200x763">
							<img class="img" src="img/map/m1.jpg" itemprop="thumbnail" />
						</a>
						<figcaption itemprop="caption description">1F</figcaption>
					</figure>

					<figure itemscope>
						<a href="img/map/m2.jpg" itemprop="contentUrl" data-size="1200x865">
							<img class="img" src="img/map/m2.jpg" itemprop="thumbnail" />
						</a>
						<figcaption itemprop="caption description">2F</figcaption>
					</figure>

					<figure itemscope>
						<a href="img/map/m3.jpg" itemprop="contentUrl" data-size="1200x774">
							<img class="img" src="img/map/m3.jpg" itemprop="thumbnail" />
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
		</div>
		<div class="foot">
			<iframe frameborder=0 width="100%" height="58" marginheight=0 marginwidth=0 scrolling=no src="footer.html"></iframe>
		</div>
		
		<!--登录-->
		<#include "login_div.ftl" />
		
		<script type="text/javascript" src="assets/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="assets/swiper/swiper.min.js"></script>
		<script type="text/javascript" src="assets/Bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/Bootstrap/js/bootsnav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/layer.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/drag_able.js"></script>
		<script type="text/javascript" src="js/jquery.imgbox.pack.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript" src="assets/photoswipe/photoswipe.min.js"></script>
		<script type="text/javascript" src="assets/photoswipe/photoswipe-ui-default.min.js"></script>
		<script>
			$("#example1").imgbox();
			$("#example2").imgbox();
			$("#example3").imgbox();
			
			var mySwiper = new Swiper('.swiper1', {
				autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
			})

			var mySwiper = new Swiper('.swiper2', {
				pagination: '.swiper-pagination',
				paginationClickable: true,
				autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
				prevButton: '.swiper-button-prev',
				nextButton: '.swiper-button-next'
			})
		</script>
		
		<script>
			var initPhotoSwipeFromDOM = function(gallerySelector) {

				// parse slide data (url, title, size ...) from DOM elements 
				// (children of gallerySelector)
				var parseThumbnailElements = function(el) {
					console.log(el);
					var thumbElements = el.childNodes,
						numNodes = thumbElements.length,
						items = [],
						figureEl,
						childElements,
						linkEl,
						size,
						item;

					for(var i = 0; i < numNodes; i++) {

						figureEl = thumbElements[i]; // <figure> element

						// include only element nodes 
						if(figureEl.nodeType !== 1) {
							continue;
						}

						linkEl = figureEl.children[0]; // <a> element

						size = linkEl.getAttribute('data-size').split('x');
						console.log(size);
						// create slide object
						item = {
							src: linkEl.getAttribute('href'),
							w: parseInt(size[0], 10),
							h: parseInt(size[1], 10)
						};

						if(figureEl.children.length > 1) {
							// <figcaption> content
							item.title = figureEl.children[1].innerHTML;
						}

						if(linkEl.children.length > 0) {
							// <img> thumbnail element, retrieving thumbnail url
							item.msrc = linkEl.children[0].getAttribute('src');
						}

						item.el = figureEl; // save link to element for getThumbBoundsFn
						items.push(item);
					}

					return items;
				};

				// find nearest parent element
				var closest = function closest(el, fn) {
					return el && (fn(el) ? el : closest(el.parentNode, fn));
				};

				// triggers when user clicks on thumbnail
				var onThumbnailsClick = function(e) {
					e = e || window.event;
					e.preventDefault ? e.preventDefault() : e.returnValue = false;

					var eTarget = e.target || e.srcElement;

					var clickedListItem = closest(eTarget, function(el) {
						return el.tagName === 'FIGURE';
					});

					if(!clickedListItem) {
						return;
					}

					// find index of clicked item
					var clickedGallery = clickedListItem.parentNode,
						childNodes = clickedListItem.parentNode.childNodes,
						numChildNodes = childNodes.length,
						nodeIndex = 0,
						index;

					for(var i = 0; i < numChildNodes; i++) {
						if(childNodes[i].nodeType !== 1) {
							continue;
						}

						if(childNodes[i] === clickedListItem) {
							index = nodeIndex;
							break;
						}
						nodeIndex++;
					}

					if(index >= 0) {
						openPhotoSwipe(index, clickedGallery);
					}
					return false;
				};

				// parse picture index and gallery index from URL (#&pid=1&gid=2)
				var photoswipeParseHash = function() {
					var hash = window.location.hash.substring(1),
						params = {};

					if(hash.length < 5) {
						return params;
					}

					var vars = hash.split('&');
					for(var i = 0; i < vars.length; i++) {
						if(!vars[i]) {
							continue;
						}
						var pair = vars[i].split('=');
						if(pair.length < 2) {
							continue;
						}
						params[pair[0]] = pair[1];
					}

					if(params.gid) {
						params.gid = parseInt(params.gid, 10);
					}

					if(!params.hasOwnProperty('pid')) {
						return params;
					}
					params.pid = parseInt(params.pid, 10);
					return params;
				};

				var openPhotoSwipe = function(index, galleryElement, disableAnimation) {
					var pswpElement = document.querySelectorAll('.pswp')[0],
						gallery,
						options,
						items;

					items = parseThumbnailElements(galleryElement);

					// define options (if needed)
					options = {
						index: index,

						// define gallery index (for URL)
						galleryUID: galleryElement.getAttribute('data-pswp-uid'),

						getThumbBoundsFn: function(index) {
							// See Options -> getThumbBoundsFn section of docs for more info
							var thumbnail = items[index].el.getElementsByTagName('img')[0], // find thumbnail
								pageYScroll = window.pageYOffset || document.documentElement.scrollTop,
								rect = thumbnail.getBoundingClientRect();

							return { x: rect.left, y: rect.top + pageYScroll, w: rect.width };
						},

						// history & focus options are disabled on CodePen
						// remove these lines in real life: 
						historyEnabled: false,
						focus: false

					};

					if(disableAnimation) {
						options.showAnimationDuration = 0;
					}

					// Pass data to PhotoSwipe and initialize it
					gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
					gallery.init();
				};

				// loop through all gallery elements and bind events
				var galleryElements = document.querySelectorAll(gallerySelector);

				for(var i = 0, l = galleryElements.length; i < l; i++) {
					galleryElements[i].setAttribute('data-pswp-uid', i + 1);
					galleryElements[i].onclick = onThumbnailsClick;
				}

				// Parse URL and open gallery if it contains #&pid=3&gid=1
				var hashData = photoswipeParseHash();
				if(hashData.pid > 0 && hashData.gid > 0) {
					openPhotoSwipe(hashData.pid - 1, galleryElements[hashData.gid - 1], true);
				}
			};

			// execute above function
			initPhotoSwipeFromDOM('.my-simple-gallery');
		</script>
	</body>

</html>