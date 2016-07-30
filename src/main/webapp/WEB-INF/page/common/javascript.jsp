<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=request.getContextPath()%>/resources/js/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.js"></script>

<!-- Datatables -->
<script src="<%=request.getContextPath()%>/resources/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/datatables/media/js/dataTables.bootstrap.min.js"></script>

<!-- Common js -->
<script src="<%=request.getContextPath()%>/resources/js/common.js"></script>

<!-- bootbox js -->
<script src="<%=request.getContextPath()%>/resources/js/bootbox.min.js"></script>

<!-- notify js -->
<script src="<%=request.getContextPath()%>/resources/js/notify.min.js"></script>

<!-- datepicker js -->
<script src="<%=request.getContextPath()%>/resources/js/bootstrap-datepicker.js"></script>

<!-- smoke js -->
<script src="<%=request.getContextPath()%>/resources/js/smoke.min.js"></script>

<!-- Bootstrap dialog multiple fix -->
<script type="text/javascript">
	$(document).on('show.bs.modal', '.modal', function () {
	    var zIndex = 1040 + (10 * $('.modal:visible').length);
	    $(this).css('z-index', zIndex);
	    setTimeout(function() {
	        $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
	    }, 0);
	});
	
	$(document).on('hidden.bs.modal', '.modal', function () {
	    $('.modal:visible').length && $(document.body).addClass('modal-open');
	});
</script>

<!-- Setup to include csrf in ajax request -->
<script type="text/javascript">

(function() {
	
	var obj = new Object();
	obj[$("meta[name='_csrf_header']").attr("content")] = $("meta[name='_csrf']").attr("content");
	
	$.ajaxSetup({
		headers: obj
	});
	
})();

</script>