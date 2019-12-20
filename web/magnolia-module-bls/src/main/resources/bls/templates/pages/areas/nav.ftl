[#assign pageMap = cmsfn.page(content)!]
<div id='navmenu' class="navigation">
    [#assign rootPage = cmsfn.siteRoot(pageMap)! ]
    [#--[#assign rootPage = cmsfn.root(content, "mgnl:page")! ]--]
    [#--[#assign rootPage = cmsfn.siteRoot(content, "homeRoot")! ]--]

    [@navigation navParentItem=rootPage depth=2 expandAll=true navClass="" /]

</div>


[#-- Basic navigation macro which generates simple navigation. You can adjust it to fulfil your needs. --]
[#macro navigation navParentItem depth=1 expandAll=false navClass="nav"]
    [#if navParentItem?has_content && depth > 0]
        [#assign navItems = navfn.navItems(navParentItem)]
        [#if navItems?has_content]
        <ul class="${navClass}" >

            [#list navItems as navItem]
                [#assign hidden = navItem.hideInNavigation!false ]
                [#if !hidden]
                    [#assign activeNavItem = navfn.isActive(content, navItem)] [#-- Active navigation item is the one which is same as current page--]
                    [#assign openNavItem = navfn.isOpen(content, navItem)] [#-- Open navigation item is the one which is ancestor of current page--]

                    <li>
                        <a href="${navfn.link(navItem)!"#"}">${cmsfn.decode(navItem).navigationTitle!cmsfn.decode(navItem).title!navItem.@name}</a>

                        [#if expandAll || activeNavItem || openNavItem]
                            [@navigation navItem depth-1 expandAll=true "subnav hidden" /]
                        [/#if]
                    </li>
                [/#if]
            [/#list]
        </ul>
        [/#if]
    [/#if]
[/#macro]
