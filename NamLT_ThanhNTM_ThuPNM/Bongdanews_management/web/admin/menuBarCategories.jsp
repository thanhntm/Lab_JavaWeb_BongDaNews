
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Order</title>
        <style>
            
        .cf:after {
            visibility: hidden;
            display: block;
            font-size: 0;
            content: " ";
            clear: both;
            height: 0;
        }

        * html .cf {
            zoom: 1;
        }

        *:first-child + html .cf {
            zoom: 1;
        }

        html {
            margin: 0;
            padding: 0;
        }

        h1 {
            font-size: 1.75em;
            margin: 0 0 0.6em 0;
        }

        a {
            color: #2996cc;
        }

        a:hover {
            text-decoration: none;
        }

        .small {
            color: #666;
            font-size: 0.875em;
        }

        .large {
            font-size: 1.25em;
        }
        .body{
            overflow: auto;
        }
        </style>
    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        
        <link href="src/plugins/nestable/jquery-nestable.css" rel="stylesheet" type="text/css">
        <script src="src/plugins/nestable/jquery.nestable.js"></script>
        <script>
            $("#page_name").text("CATEGORY ORDER");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="panel-body">
                    <menu id="nestable-menu">
                        <button type="button" class="btn btn-primary" data-action="expand-all">Expand All</button>
                        <button type="button" class="btn btn-primary" data-action="collapse-all">Collapse All</button>
                        <button type="button" class="btn btn-success" style="float: right;margin-right: 50px;" onclick="saveMap()">Update</button>
                    </menu>
                    <div class="cf nestable-lists row">
                        <div class="panel-default col-sm-6">
                            <div class="card">
                                <div class="header"><h4>Current categories in Menu bar</h4></div>
                                <div class="body">
                                    <div class="dd" id="menuBarHolder">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-default col-sm-6">
                            <div class="card">
                                <div class="header"><h4>Remain categories</h4></div>
                                <div class="body">
                                    <div class="dd" id="hiddenHolder">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="src/js/CategoryBussiness/MenuCategoryFunctions.js"></script>
    </body>
</html>
