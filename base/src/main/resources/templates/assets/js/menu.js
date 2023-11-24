$(document).ready(function() {
    const mobileMenuIcon = $('#mobileMenuIcon');
    const headerMenu = $('#headerMenu');

    mobileMenuIcon.click(function() {
        headerMenu.toggleClass('active');
    });
});