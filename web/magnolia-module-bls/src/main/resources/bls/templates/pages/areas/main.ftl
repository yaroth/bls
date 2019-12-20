<!-- main Container -->
<div id="container">
    <nav title="nach oben scrollen">
        <div id="nav-container">
            <a id="navbar-logo" href="/home"><img src="${ctx.contextPath}/.resources/geologix/webresources/img/logo.svg"></a>
            <div id="navbar-menu">
                <span>menu</span>
                <img src="${ctx.contextPath}/.resources/geologix/webresources/img/menu.svg" class="menu" title="Menu">
                <img src="${ctx.contextPath}/.resources/geologix/webresources/img/close.svg" class="close" title="Close">
            </div>
        </div>
    </nav>
    <div id="main-content">
        [@cms.area name="rowsArea"/]
    </div>

</div>