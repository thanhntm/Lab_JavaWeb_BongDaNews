function createNewCategory(formId) {
    var rs = confirm("Do you want to create new Category?");
    if (rs == true) {
        var form = $('#' + formId);
        $.ajax({
            url: form.attr('action'),
            type: form.attr('method'),
            data: form.serialize(),
            success: function (data) {
                if ($.trim(data) !== "fail") {
                    form[0].reset();
                    alert("Add new Category successfully!");
                    getAllCategory();
                } else {
                    alert("Something went wrong! Please try again!");
                }
            }
        })
    }
}

function getAllCategory() {
    $.ajax({
        url: "GetAllCategory",
        method: "GET",
        async: false,
        success: function (data) {
            if (String(data) != "fail") {
                var table = $("<table class='table table-bordered table-striped table-hover js-basic-example dataTable' id='dataTable'/>");
                table.append("<thead><tr>\n\
                    <th>ID</th><th>Name</th><th>Description</th><th>Mother category</th><th>Status</th><th></th>\n\
                    </tr></thead>");
                var tbody = $("<tbody/>");
                var row = null;
                for (i in data) {
                    row = $("<tr/>");
                    row.append("<td>" + data[i].id + "</td>");
                    row.append("<td>" + data[i].categoryName + "</td>");
                    row.append("<td>" + data[i].description + "</td>");
                    row.append("<td>" + (data[i].motherCategory.id != 0 ? data[i].motherCategory.categoryName : "None") + "</td>");
                    row.append("<td>" + defineCategoryStatus(data[i].status) + "</td>");
                    row.append("<td></td>");
                    tbody.append(row);
                }
                table.append(tbody);
                $("#dataHolder").html(table);

                $('.js-basic-example').DataTable({
                    responsive: true
                });
            }
        }
    });
}

function defineCategoryStatus(status) {
    var result = "<span style='color:green'>SHOW</span>";
    switch (status) {
        case -1:
            result = "<span style='color:red'>DISABLE</span>";
            break;
        case 0:
            result = "<span style='color:blue'>HIDDEN</span>";
            break;
    }
    return result;
}

function refreshCategoryData() {
    var searchValue = $("#dataHolder").find("input[type=search]").first().val();
    getAllCategory();
    var searchInput = $("#dataHolder").find("input[type=search]").first();
    searchInput.val(searchValue);
    searchInput.keypress();
    searchInput.keyup();
}
