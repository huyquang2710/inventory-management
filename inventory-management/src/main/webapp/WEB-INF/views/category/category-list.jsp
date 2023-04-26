<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
      <div class="right_col" role="main">
        <div class="">
          <div class="clearfix"></div>
          <div class="col-md-12 col-sm-12  ">
            <div class="x_panel">
              <div class="x_title">
                <h2>Category List</h2>
                <div class="clearfix"></div>
              </div>

              <div class="x_content">
                <a href="<c:url value=" /category/add" />" class="btn btn-app"><i class="fa fa-plus"></i>Add</a>
                <!-- search start -->
                <div class="container" style="padding: 20px;">
                  <form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left"
                    class="form-horizontal form-label-left" servletRelativeAction="/category/list" method="POST">
                    <div class="item form-group">
                      <label class="col-form-label col-md-3 col-sm-3 label-align" for="code">ID 
                      </label>
                      <div class="col-md-6 col-sm-6 ">
                        <form:input path="id" class="form-control" style="text-transform:uppercase"/>
                      </div>
                    </div>
                    <div class="item form-group">
                      <label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Code 
                      </label>
                      <div class="col-md-6 col-sm-6 ">
                        <form:input path="code" class="form-control"  />
                      </div> 
                    </div>
                    <div class="item form-group">
                      <label for="desciption" class="col-form-label col-md-3 col-sm-3 label-align">Name</label>
                      <div class="col-md-6 col-sm-6 ">
                        <form:input path="name" class="form-control" />
                      </div>
                    </div>
                    <div class="item form-group">
                      <div class="col-md-6 col-sm-6 offset-md-3">
                          <button type="submit" class="btn btn-success">Search</button>
                      </div>
                    </div>

                  </form:form>
                </div>
                <!-- search end -->

                <div class="table-responsive">
                  <table class="table table-striped jambo_table bulk_action">
                    <thead>
                      <tr class="headings">
                        <th class="column-title"># </th>
                        <th class="column-title">Id </th>
                        <th class="column-title">Code </th>
                        <th class="column-title">Name </th>
                        <th class="column-title">Desctiption </th>
                        <th class="column-title">Date Created </th>
                        <th class="column-title no-link last text-center" colspan="3"><span class="nobr">Action</span>
                        </th>
                      </tr>
                    </thead>

                    <tbody>
                      <c:forEach items="${categories }" var="category" varStatus="loop">
                        <c:choose>
                          <c:when test="${loop.index %2 == 0 }">
                            <tr class="even pointer">
                          </c:when>
                          <c:otherwise>
                            <tr class="odd pointer">
                          </c:otherwise>
                        </c:choose>
                        <td class=" ">${loop.index + 1 }</td>
                        <td class=" ">${ category.id }</td>
                        <td class=" ">${ category.code }</td>
                        <td class=" ">${ category.name }</td>
                        <td class=" ">${ category.description }</td>
                        <td class=" ">${ category.createDate }</td>
                        <td class="text-center"><a href="<c:url value=" /category/view/${category.id }" />" class="btn
                          btn-round btn-default">View</a></td>
                        <td class="text-center"><a href="<c:url value=" /category/edit/${category.id }" />" class="btn
                          btn-round btn-primary">Edit</a></td>
                        <td class="text-center"><a href="javascript:void(0);" onclick="confirmDelete(${category.id });"
                            class="btn btn-round btn-danger">Delete</a></td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <script>
        function confirmDelete(id) {
          if (confirm("Do you want delete this record??")) {
            window.location.href = '<c:url value="/category/delete/" />' + id;
          }
        }
        $(document).ready(function () {
          processMessage();
        });

        function processMessage() {
          var successMsg = '${successMsg}';
          var errorMsg = '${errorMsg}';
          if (successMsg) {
            new PNotify({
              title: 'Success',
              text: successMsg,
              type: 'success',
              styling: 'bootstrap3'
            });
          }
          if (errorMsg) {
            new PNotify({
              title: 'Error',
              text: errorMsg,
              type: 'error',
              styling: 'bootstrap3'
            });
          }
        }

      </script>