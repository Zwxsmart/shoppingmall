<div id="slider" class="mui-slider" >
	<div class="mui-slider-group">
	<#if banners??>
	    <#list banners as b>
	    	<div class="mui-slider-item">
				<a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>">
					<img src="${imgPath+b.picUrl}">
				</a>
			</div>
	    </#list>
    </#if>
	</div>
	<div class="mui-slider-indicator">
		<#if banners??>
		    <#list banners as b>
		    	<#if b_index == 0>
					<div class="mui-indicator mui-active"></div>
				<#else>
					<div class="mui-indicator"></div>
				</#if>
		    </#list>
	    </#if>
	</div>
</div>
