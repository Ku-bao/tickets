// src/main/resources/static/js/toast.js

function showErrorToast(message) {
    var toastHtml = '<div class="toast" role="alert" aria-live="assertive" aria-atomic="true" data-delay="3000">' +
                        '<div class="toast-header">' +
                            '<strong class="mr-auto">Error</strong>' +
                            '<button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">' +
                                '<span aria-hidden="true">&times;</span>' +
                            '</button>' +
                        '</div>' +
                        '<div class="toast-body">' + message + '</div>' +
                    '</div>';

    $('#toast-container').append(toastHtml);
    $('.toast').toast('show').on('hidden.bs.toast', function() {
        $(this).remove();
    });
}