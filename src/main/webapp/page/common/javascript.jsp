<!-- jQuery -->
<script src="<%=request.getContextPath()%>/resources/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=request.getContextPath()%>/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=request.getContextPath()%>/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Morris Charts JavaScript -->
<%-- <script src="<%=request.getContextPath()%>/resources/bower_components/raphael/raphael-min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bower_components/morrisjs/morris.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/morris-data.js"></script> --%>

<!-- Custom Theme JavaScript -->
<script src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>

<!-- Datatables -->
<script src="<%=request.getContextPath()%>/resources/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/datatables/media/js/dataTables.bootstrap.min.js"></script>

<!-- Common js -->
<script src="<%=request.getContextPath()%>/resources/common.js"></script>

<!-- bootbox js -->
<script src="<%=request.getContextPath()%>/resources/bootbox.min.js"></script>

<!-- notify js -->
<script src="<%=request.getContextPath()%>/resources/notify.min.js"></script>

<!-- datepicker js -->
<script src="<%=request.getContextPath()%>/resources/bootstrap-datepicker.js"></script>

<!-- smoke js -->
<script src="<%=request.getContextPath()%>/resources/smoke.min.js"></script>

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