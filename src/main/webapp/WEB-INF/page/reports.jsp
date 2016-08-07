<div class="row">
	<ul>
		<li><a href="javascript:void(0);" onclick="windowReport('owner/report');" >List of owners</a></li>
		<li><a href="javascript:void(0);" onclick="windowReport('visit/report');" >List of visits</a></li>
	</ul>
</div>

<script type="text/javascript">
	function windowReport( path ) {
		var win = window.open( path, '_blank');
		win.focus();
		return false;
	}
</script>