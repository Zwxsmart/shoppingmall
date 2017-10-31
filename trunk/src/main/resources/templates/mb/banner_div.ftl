<div id="slider" class="mui-slider" >
	<div class="mui-slider-group mui-slider-loop">
	    <#if banners??>
	    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
	    <#list banners as b>
	    	<#if !b_has_next>
			<div class="mui-slider-item mui-slider-item-duplicate">
	      		<a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>"><img src="${imgPath+b.picUrl}"></a>
	    	</div>
			</#if>
		</#list>
		<#list banners as b>
			<div class="mui-slider-item"><a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>"><img src="${imgPath+b.picUrl}"></a></div>
		</#list>
	    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
	    <#list banners as b>
	    	<#if b_index == 0>
			<div class="mui-slider-item mui-slider-item-duplicate">
	      		<a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>"><img src="${imgPath+b.picUrl}"></a>
	    	</div>
			</#if>
		</#list>
		</#if>
	</div>
	<div class="mui-slider-indicator">
		<#list banners as b>
	    	<#if b_index == 0>
				<div class="mui-indicator mui-active"></div>
			<#else>
				<div class="mui-indicator"></div>
			</#if>
		</#list>
	</div>
</div>