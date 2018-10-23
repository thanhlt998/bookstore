$(document).ready(function() {
  // Validate form
  // ------------------------------------------------------------
  // var contextPath = $("#context-path").attr("context-path");
  var registerForm = $("#registerForm");

  validatePasswordRegisterForm(registerForm);

  registerForm.submit(validateSubmitRegisterForm);

  $("#registerForm")
    .find($("#username"))
    .blur(validateUsernameField);

  // Add cart ------------------------------------------------------------
  $("#add-button").click(increaseQuantity);
  $("#minus-button").click(decreaseQuantity);

  // Replace img ---------------------------------------------------------
  $(".small-img").click(replaceImg);

  $("#shopCart").hover(
    function() {
      $("#cartDetail")
        .stop()
        .slideDown();
    },
    function() {
      $("#cartDetail")
        .stop()
        .slideUp();
    }
  );

  $("#cartDetail")
    .on(
      "hover",
      ".remove-cart-item",
      function(event) {
        $(this)
          .stop()
          .animate(
            {
              "font-size": "120%"
            },
            300,
            "swing"
          );
      },
      function(event) {
        $(this)
          .stop()
          .animate({
            "font-size": "100%"
          });
      }
    )
    .on("click", ".remove-cart-item", removeCartItem);

  //cart
  $(".add-cart").click(addCart);

  $(".fa-edit").click(editChange);

  //Order
  $("#order-info-button").click(clickOrderInfoButton);

  $("#confirm-order-button").click(createOrder);

  //Profile
  $(".list-group-item").click(changeUserFunction);

  $("#save-changes-button").click(updateUser);

  $("#profile")
    .on("change", ".changeField", changeInformation)
    .change();

  $("#view-order-history-button").click(viewOrderHistory);

  enableChangePasswordValidate($("#change-password"));

  $("#view-order-button").click(viewOrderDetail);

  // user management
  $("#view-all-users").on("click", ".fa-user-edit", editUserButtonClick);

  $("#view-all-users").on("click", ".fa-save", saveRoleChange);

  $("#view-all-users").on(
    "click",
    "#previous-button, #next-button",
    viewUsersButtonClick
  );

  $("#search-user-fields").on(
    "change",
    "#user-id, #username, #name, #authority",
    searchUserFieldsOnChange
  );

  //order management
  $("#view-all-orders").on(
    "click",
    "#previous-button, #next-button",
    viewOrderButtonClick
  );

  $("#search-order-fields").on(
    "change",
    "#user-id, #order-id, #order-status",
    searchOrderFieldsOnChange
  );

  $("#view-all-orders").on("click", ".fa-user-edit", editOrderButtonClick);

  $("#view-all-orders").on("click", ".fa-save", saveOrderChange);

  $("#order-id-input").blur(orderIdInputOnChange);

  $("#admin-view-order-button").click(adminViewOrderDetail);

  // category management
  $("#search-category-button").click(searchCategory);

  $("#add-category-button").click(addCategory);

  // manufaturer management
  $("#search-manufacturer-button").click(searchManufacturer);

  $("#add-manufacturer-button").click(addManufacturer);

  // book management
  $("#add-book-management").on("click", "#category-id", loadCategory);

  $("#add-book-management").on("click", "#manufacturer-id", loadManufacturer);

  // $("#add-book-management").on("click", "#add-book-button", addBook);

  $("#upload-images-form").submit(addBook);
});

// --------------------------------------------------------------------------------------------
// Validate Form

function validateSubmitRegisterForm(event) {
  var registerForm = $("#registerForm");
  var passwordField = registerForm.find($("#password"));
  var confirmPasswordField = registerForm.find($("#confirmPassword"));

  validatePasswordField(passwordField, event);
  validateConfirmPasswordField(passwordField, confirmPasswordField, event);
}

function validatePasswordRegisterForm(registerForm) {
  var passwordField = registerForm.find($("#password"));
  var confirmPasswordField = registerForm.find($("#confirmPassword"));

  passwordField.blur(function(event) {
    validatePasswordField(passwordField, event);
  });

  confirmPasswordField.blur(function(event) {
    validateConfirmPasswordField(passwordField, confirmPasswordField, event);
  });
}

function validatePasswordField(passwordField, event) {
  var password = passwordField.val();
  if (!password.length >= 8 || !/.*[0-9].*/.test(password)) {
    $("#password-feedback").text(
      "Must not contain any whitespace characters and contain at least 8 characters include at least a number."
    );
    event.preventDefault();
  } else {
    $("#password-feedback").text("");
  }
}

function validateConfirmPasswordField(
  passwordField,
  confirmPasswordField,
  event
) {
  var password = passwordField.val();
  var confirmPassword = confirmPasswordField.val();
  if (password !== confirmPassword) {
    $("#confirmPasswordAppend").html(
      "<i class='far fa-times-circle text-danger'></i>"
    );
    event.preventDefault();
  } else {
    $("#confirmPasswordAppend").html(
      "<i class='far fa-check-circle text-success'></i>"
    );
  }
}

function validateUsernameField(event) {
  event.preventDefault();
  var contextPath = $(this).attr("context-path");
  var username = $(this).val();
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/checkAvailableUsername",
    data: {
      username: username
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      if (data === true) {
        $("#usernameAppend").html(
          "<i class='far fa-check-circle text-success'></i>"
        );
        event.preventDefault();
      } else {
        $("#usernameAppend").html(
          "<i class='far fa-times-circle text-danger'></i>"
        );
      }
    },
    error: function(e) {
      console.log(e);
    }
  });
}

// ------------------------------------------------------------------------------------------------------------------------

// add cart
function increaseQuantity() {
  var quantityAdd = $("#quantity-add");
  var currentValue = parseInt(quantityAdd.val());
  quantityAdd.val(currentValue + 1);
  $("#add-cart-button").attr("quantity", quantityAdd.val());
}

function decreaseQuantity() {
  var quantityAdd = $("#quantity-add");
  var currentValue = parseInt(quantityAdd.val());
  if (currentValue > 1) {
    quantityAdd.val(currentValue - 1);
  }
  $("#add-cart-button").attr("quantity", quantityAdd.val());
}

function addCart(event) {
  var bookId = $(this).attr("bookid");
  var quantity = $(this).attr("quantity");
  var contextPath = $(this).attr("context-path");
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addCart",
    data: {
      bookId: bookId,
      quantity: quantity
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      if (data === false) {
        alert("Cannot add more this book.");
      } else {
        console.log(data.cart);
        var cart = data.cart;
        $("#badge").text(data.totalQuantity);

        var tableBody = $("#cartDetail").find("tbody");
        $("#cartDetail").css("height", "");
        tableBody.empty();
        for (var i = 0; i < cart.length; i++) {
          $("<tr>")
            .append($("<td>").text(cart[i].book.bookName))
            .append($("<td>").text(cart[i].quantity))
            .append(
              $("<td>")
                .addClass("text-danger align-middle")
                .append(
                  $("<i>")
                    .addClass("far fa-times-circle remove-cart-item")
                    .attr("book-id", cart[i].book.bookId)
                    .attr("context-path", contextPath)
                )
            )
            .appendTo(tableBody);
        }
        $("<tr>")
          .append($("<td>").text("Total Price"))
          .append(
            $("<td>")
              .attr("colspan", "2")
              .text(data.totalPrice)
          )
          .appendTo(tableBody);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function removeCartItem(event) {
  var bookId = $(this).attr("book-id");
  var removeIcon = $(this);
  var contextPath = $(this).attr("context-path");
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/removeCartItem",
    data: {
      bookId: bookId
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      removeIcon
        .parent()
        .parent()
        .remove();
      $("#shopCart")
        .find("td")
        .last()
        .text(data.totalPrice);
      $("#badge").text(data.totalQuantity);
      $("#cartDetail").css("height", "");
    },
    error: function(error) {
      console.log(error);
    }
  });
}

// confirm order

function clickOrderInfoButton(event) {
  $(this)
    .parent()
    .parent()
    .find("td")
    .first()
    .text("Additional Information");
  $(this).css("display", "none");
  $(this)
    .parent()
    .find(".info-form")
    .slideDown();
}

function isValidOrderInfo(shipAddress, paymentMethod) {
  var flag = true;
  if (shipAddress === "") {
    $("#ship-address-feedback").text("Please fill in this field");
    flag = false;
  }
  if (paymentMethod === "") {
    $("#payment-method-feedback").text("Please fill in this field");
    flag = false;
  }
  return flag;
}

function createOrder(event) {
  var shipAddress = $("#shipAddress").val();
  var paymentMethod = $("#paymentMethod").val();
  var contextPath = $(this).attr("context-path");
  var message = "";
  console.log(shipAddress + " " + paymentMethod);
  if (isValidOrderInfo(shipAddress, paymentMethod)) {
    $.ajax({
      type: "GET",
      async: false,
      contentType: "application/json",
      url: contextPath + "/createOrder",
      data: {
        shipAddress: shipAddress,
        paymentMethod: paymentMethod
      },
      dataType: "json",
      timeout: 10000,
      success: function(data) {
        console.log(data);
        if (data === false) {
          message =
            "Your order is not completed! Please add Cart and order again!";
        } else {
          message = "Your order is ordered successfully!";
        }
      },
      error: function(error) {
        message = "Some errors occured!";
        console.log(error);
      }
    });
  } else {
    console.log("blank content");
  }
  alert(message);
  $(this).prop("disabled", true);
  $(location).attr("href", event.data.url);
}

// small-picture
// --------------------------------------------------------------------------------------------
function replaceImg(event) {
  var mainImg = $("#main-img");
  mainImg.attr("src", $(this).attr("src"));
}

// Profile
// -------------------------------------------------------------------------------------------------

function changeUserFunction(event) {
  var sibling = $(this).siblings(".active");
  sibling.removeClass("active");
  $(sibling.attr("data-target")).css("display", "none");

  $(this).addClass("active");

  console.log($(this).attr("data-target"));
  $($(this).attr("data-target"))
    .stop()
    .slideDown(400, "swing");
}

function editChange(event) {
  $(this)
    .parent()
    .parent()
    .find(".inputChange")
    .slideToggle(400, "linear");
}

function changeInformation(event) {
  var changeField = $(this);
  $("#save-changes-button").prop("disabled", false);
  changeField
    .parent()
    .parent()
    .find("span")
    .text(changeField.val());
}

//profile
function updateUser(event) {
  var contextPath = $(this).attr("context-path");
  var tableProfile = $("#table-profile");
  var name = tableProfile.find("#name").val();
  var email = tableProfile.find("#email").val();
  var birthDate = tableProfile.find("#birthDate").val();
  var gender = tableProfile.find("#gender").val();
  var address = tableProfile.find("#address").val();
  var phone = tableProfile.find("#phone").val();
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/updateProfile",
    data: {
      name: name,
      email: email,
      birthDate: birthDate,
      gender: gender,
      address: address,
      phone: phone
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        tableProfile
          .find("#table-profile-feedback")
          .text("Your profile is updated!");
        $(this).attr("disabled", true);
      }
    },
    error: function(error) {
      tableProfile
        .find("#table-profile-feedback")
        .text("Your profile cannot be changed!");
    }
  });
}

function viewOrderHistory(event) {
  var historyTableBody = $("#order-history").find("tbody");
  var contextPath = $(this).attr("context-path");
  var page = $(this).attr("page");
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/viewOrderHistory",
    data: {
      page: page
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].orderId))
          .append($("<td>").text(dateFormat(data[i].orderDate)))
          .append($("<td>").text(dateFormat(data[i].shipDate)))
          .append($("<td>").text(data[i].shipAddress))
          .append($("<td>").text(data[i].totalAmount))
          .append($("<td>").text(data[i].paymentMethod))
          .append($("<td>").text(data[i].status))
          .appendTo(historyTableBody);
      }

      if (data.length < 10) {
        $(this).attr("disabled", true);
      } else {
        $(this).text("View More");
        $(this).attr("page", page + 1);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function dateFormat(milliseconds) {
  var date = new Date(milliseconds);
  return (
    date.getFullYear() +
    "-" +
    (date.getMonth() < 10 ? "0" + date.getMonth() : date.getMonth()) +
    "-" +
    (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
  );
}

// validate change password

function validateNewPassword(event) {
  var password = $(this).val();
  if (validNewPassword(password)) {
    $("#new-password-feedback").text("");
  } else {
    $("#new-password-feedback").text(
      "Password must not contain any whitespace and at least 8 characters (include number)"
    );
  }
}

function validNewPassword(password) {
  return password.length >= 8 && /.*[0-9].*/.test(password);
}

function validConfirmNewPassword(event) {
  var confirmNewPassword = $(this).val();
  var newPassword = event.data.newPassword.val();
  if (confirmNewPassword === newPassword) {
    $("#confirm-new-password-feedback").text("");
  } else {
    $("#confirm-new-password-feedback").text(
      "This field must match new password field."
    );
  }
}

function changePasswordFunction(event) {
  var oldPassword = event.data.oldPassword.val();
  var newPassword = event.data.newPassword.val();
  var confirmNewPassword = event.data.confirmNewPassword.val();
  var url = $(this).attr("context-path");
  if (newPassword !== confirmNewPassword) {
    event.preventDefault();
  } else {
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: url + "/changePassword",
      data: {
        newPassword: newPassword,
        oldPassword: oldPassword
      },
      dataType: "json",
      timeout: 10000,
      success: function(data) {
        if (data === true) {
          $("#change-password-feedback").text("Your password is updated!");
        } else {
          $("#old-password-feedback").text("Old password isn't correct!");
          $("#new-password").empty();
          $("confirm-new-password").empty();
          $("#change-password-feedback").text("Fail to update your password!");
        }
      },
      error: function(error) {
        console.log(error);
        $("#change-password-feedback").text("Fail to update your password!");
      }
    });
  }
}

function enableChangePasswordValidate(changePassword) {
  var oldPassword = changePassword.find("#old-password");
  var newPassword = changePassword.find("#new-password");
  var confirmNewPassword = changePassword.find("#confirm-new-password");
  var button = changePassword.find("#change-password-button");

  newPassword.blur(validateNewPassword);
  confirmNewPassword.blur(
    {
      newPassword
    },
    validConfirmNewPassword
  );
  button.click(
    {
      oldPassword: oldPassword,
      newPassword: newPassword,
      confirmNewPassword: confirmNewPassword
    },
    changePasswordFunction
  );
}

function viewOrderDetail(event) {
  var orderDetail = $("#order-detail");
  var orderId = orderDetail.find("#order-id-input").val();
  var contextPath = $(this).attr("context-path");
  var orderDetailView = orderDetail.find("#order-detail-view");
  var tableBody = orderDetail.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/viewOrderDetail",
    data: {
      orderId: orderId
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === false) {
        orderDetailView.css("display", "none");
        orderDetail
          .find("#order-detail-feedback")
          .text(`No Order with orderId = ${orderId} found.`);
      } else {
        orderDetail.find("#order-detail-feedback").empty();
        var user = data.user;
        var order = data.order;
        var ordersDetailItemList = data.ordersDetailItemList;
        orderDetailView.find(".name").text(user.name);
        orderDetailView.find(".email").text(user.email);
        orderDetailView.find(".phone").text(user.phone);
        orderDetailView.find(".address").text(order.shipAddress);
        orderDetailView.find(".paymentMethod").text(order.paymentMethod);
        orderDetailView.find(".shipDate").text(dateFormat(order.shipDate));

        tableBody.empty();
        for (var i = 0; i < ordersDetailItemList.length; i++) {
          $("<tr>")
            .append($("<td>").text(i + 1))
            .append($("<td>").text(ordersDetailItemList[i].bookName))
            .append(
              $("<td>").text(ordersDetailItemList[i].ordersDetail.quantity)
            )
            .append(
              $("<td>").text(ordersDetailItemList[i].ordersDetail.pricePerUnit)
            )
            .append(
              $("<td>").text(
                ordersDetailItemList[i].ordersDetail.quantity *
                  ordersDetailItemList[i].ordersDetail.pricePerUnit
              )
            )
            .appendTo(tableBody);
        }
        $("<tr>")
          .append(
            $("<td>")
              .attr("colspan", 4)
              .text("Total Price")
          )
          .append($("<td>").text(order.totalAmount))
          .appendTo(tableBody);
        orderDetailView.fadeIn();
      }
    }
  });
}

function editUserButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  $(this)
    .parent()
    .parent()
    .find(".authority")
    .attr("disabled", false);
  $(this).replaceWith(
    $("<i>")
      .addClass("far fa-save")
      .attr("context-path", contextPath)
  );
}

function saveRoleChange(event) {
  var editIcon = $(this);
  var trParent = $(this)
    .parent()
    .parent();
  var newRole = trParent.find(".authority").val();
  var contextPath = $(this).attr("context-path");
  var userId = trParent
    .find("td")
    .first()
    .text();

  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/changeUserRole",
    data: {
      userId: userId,
      newRole: newRole
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        editIcon.replaceWith(
          $("<i>")
            .addClass("fas fa-user-edit")
            .attr("context-path", contextPath)
        );
        trParent.find(".authority").attr("disabled", true);
        alert(`Change the role of user with userId = ${userId} successfully!`);
      } else {
        alert("Can't change the role of this user!");
      }
    },
    error: function(error) {
      console.log(error);
      alert("Can't change the role of this user!");
    }
  });
}

function viewUsersButtonClick(event) {
  var usersManagement = $("#view-all-users");
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var tableBody = usersManagement.find("tbody");
  var userId = usersManagement.find("#user-id").val();
  var username = usersManagement.find("#username").val();
  var name = usersManagement.find("#name").val();
  var authority = usersManagement.find("#authority").val();
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/searchUsers",
    data: {
      page: page,
      userId: userId,
      username: username,
      name: name,
      authority: authority
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].userId)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].username)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].name)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].email)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(dateFormat(data[i].birthDate))
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].gender)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].address)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].phone)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(createRoleSelect(data[i].authority))
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(
                $("<i>")
                  .addClass("fas fa-user-edit align-middle")
                  .attr("context-path", contextPath)
              )
          )
          .appendTo(tableBody);
      }

      var previousButton = usersManagement.find("#previous-button");
      var nextButton = usersManagement.find("#next-button");
      nextButton.text("Next");
      if (typeButton === "next") {
        console.log(page + 1);

        if (data.length < 10) {
          nextButton.attr("disabled", true);
        } else {
          nextButton.attr("page", page + 1);
          previousButton.attr("page", page);
        }
        if (page === 0) {
          previousButton.attr("disabled", true);
        } else {
          previousButton.attr("disabled", false);
        }
      } else {
        if (page === 0) {
          previousButton.attr("disabled", true);
        } else {
          previousButton.attr("page", page - 1);
          nextButton.attr("page", page);
        }
        nextButton.attr("disabled", false);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function createRoleSelect(authority) {
  var select = $("<select>")
    .val(authority)
    .addClass("form-control authority")
    .append(
      $("<option>")
        .val("ROLE_USER")
        .text("USER")
    )
    .append(
      $("<option>")
        .val("ROLE_ADMIN")
        .text("ADMIN")
    )
    .append(
      $("<option>")
        .val("ROLE_STOCKKEEPER")
        .text("STOCKKEEPER")
    )
    .attr("disabled", true);
  select.find(`option[value=${authority}]`).attr("selected", "selected");
  return select;
}

function searchUserFieldsOnChange(event) {
  $("#view-all-users")
    .find("#next-button")
    .text("Search")
    .attr("page", 0)
    .attr("disabled", false);
  $("#view-all-users")
    .find("#previous-button")
    .attr("page", 0)
    .attr("disabled", true);
}

// admin view order history

function viewOrderButtonClick(event) {
  var ordersManagement = $("#view-all-orders");
  var typeButton = $(this).attr("type-button");
  var page = parseInt($(this).attr("page"));
  var contextPath = $(this).attr("context-path");
  var tableBody = ordersManagement.find("tbody");
  var userId = ordersManagement.find("#user-id").val();
  var orderId = ordersManagement.find("#order-id").val();
  var orderStatus = ordersManagement.find("#order-status").val();
  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/searchOrders",
    data: {
      page: page,
      orderId: orderId,
      userId: userId,
      orderStatus: orderStatus
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      console.log(data);
      tableBody.empty();
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].orderId)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].userId)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(dateFormat(data[i].orderDate))
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(
                $("<input>")
                  .addClass("form-control")
                  .attr("type", "date")
                  .val(dateFormat(data[i].shipDate))
                  .attr("disabled", true)
                  .attr("id", "ship-date")
              )
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].shipAddress)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].totalAmount)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .text(data[i].paymentMethod)
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(createOrderStatusSelect(data[i].orderStatus))
          )
          .append(
            $("<td>")
              .addClass("align-middle")
              .append(
                $("<i>")
                  .addClass("fas fa-user-edit align-middle")
                  .attr("context-path", contextPath)
              )
          )
          .appendTo(tableBody);
      }

      var previousButton = ordersManagement.find("#previous-button");
      var nextButton = ordersManagement.find("#next-button");
      nextButton.text("Next");
      if (typeButton === "next") {
        console.log(page + 1);

        if (data.length < 10) {
          nextButton.attr("disabled", true);
        } else {
          nextButton.attr("page", page + 1);
          previousButton.attr("page", page);
        }
        if (page === 0) {
          previousButton.attr("disabled", true);
        } else {
          previousButton.attr("disabled", false);
        }
      } else {
        if (page === 0) {
          previousButton.attr("disabled", true);
        } else {
          previousButton.attr("page", page - 1);
          nextButton.attr("page", page);
        }
        nextButton.attr("disabled", false);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function createOrderStatusSelect(orderStatus) {
  var select = $("<select>")
    .addClass("form-control")
    .attr("id", "order-status")
    .attr("disabled", true)
    .append(
      $("<option>")
        .val("PREPARING")
        .text("Preparing")
    )
    .append(
      $("<option>")
        .val("SHIPPING")
        .text("Shipping")
    )
    .append(
      $("<option>")
        .val("CANCELED")
        .text("Canceled")
    )
    .append(
      $("<option>")
        .val("FINISH")
        .text("Finish")
    );
  select.find(`option[value='${orderStatus}']`).attr("selected", "selected");
  return select;
}

function searchOrderFieldsOnChange(event) {
  $("#view-all-orders")
    .find("#next-button")
    .text("Search")
    .attr("disabled", false)
    .attr("page", 0);
  $("#view-all-orders")
    .find("#previous-button")
    .attr("disabled", true)
    .attr("page", 0);
}

function editOrderButtonClick(event) {
  var contextPath = $(this).attr("context-path");
  $(this)
    .parent()
    .parent()
    .find("#ship-date, #order-status")
    .attr("disabled", false);
  $(this).replaceWith(
    $("<i>")
      .addClass("far fa-save")
      .attr("context-path", contextPath)
  );
}

function saveOrderChange(event) {
  var editIcon = $(this);
  var trParent = $(this)
    .parent()
    .parent();
  var shipDate = trParent.find("#ship-date").val();
  var orderStatus = trParent.find("#order-status").val();
  var contextPath = $(this).attr("context-path");
  var orderId = trParent
    .find("td")
    .first()
    .text();

  $.ajax({
    type: "GET",
    async: false,
    contentType: "application/json",
    url: contextPath + "/changeOrder",
    data: {
      shipDate: shipDate,
      orderStatus: orderStatus,
      orderId: orderId
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        editIcon.replaceWith(
          $("<i>")
            .addClass("fas fa-user-edit")
            .attr("context-path", contextPath)
        );
        trParent.find("#ship-date, #order-status").attr("disabled", true);
        alert(`Change order with orderId = ${orderId} successfully!`);
      } else {
        alert("Can't change this order!");
      }
    },
    error: function(error) {
      console.log(error);
      alert("Can't change this order!");
    }
  });
}

function adminViewOrderDetail(event) {
  var orderDetail = $("#order-detail");
  var orderId = orderDetail.find("#order-id-input").val();
  var contextPath = $(this).attr("context-path");
  var orderDetailView = orderDetail.find("#order-detail-view");
  var tableBody = orderDetail.find("tbody");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/adminViewOrderDetail",
    data: {
      orderId: orderId
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === false) {
        orderDetailView.css("display", "none");
        orderDetail
          .find("#order-detail-feedback")
          .text(`No Order with orderId = ${orderId} found.`);
      } else {
        orderDetail.find("#order-detail-feedback").empty();
        var user = data.user;
        var order = data.order;
        var ordersDetailItemList = data.ordersDetailItemList;
        orderDetailView.find(".name").text(user.name);
        orderDetailView.find(".email").text(user.email);
        orderDetailView.find(".phone").text(user.phone);
        orderDetailView.find(".address").text(order.shipAddress);
        orderDetailView.find(".paymentMethod").text(order.paymentMethod);
        orderDetailView.find(".shipDate").text(dateFormat(order.shipDate));

        tableBody.empty();
        for (var i = 0; i < ordersDetailItemList.length; i++) {
          $("<tr>")
            .append($("<td>").text(i + 1))
            .append($("<td>").text(ordersDetailItemList[i].bookName))
            .append(
              $("<td>").text(ordersDetailItemList[i].ordersDetail.quantity)
            )
            .append(
              $("<td>").text(ordersDetailItemList[i].ordersDetail.pricePerUnit)
            )
            .append(
              $("<td>").text(
                ordersDetailItemList[i].ordersDetail.quantity *
                  ordersDetailItemList[i].ordersDetail.pricePerUnit
              )
            )
            .appendTo(tableBody);
        }
        $("<tr>")
          .append(
            $("<td>")
              .attr("colspan", 4)
              .text("Total Price")
          )
          .append($("<td>").text(order.totalAmount))
          .appendTo(tableBody);
        orderDetailView.fadeIn();
      }
    }
  });
}

function orderIdInputOnChange(event) {
  if ($(this).val() === "") {
    $(this)
      .parent()
      .find(".view-order-button")
      .attr("disabled", true);
  } else {
    $(this)
      .parent()
      .find(".view-order-button")
      .attr("disabled", false);
  }
}

// category-management

function searchCategory(event) {
  var viewCategory = $("#view-category");
  var categoryId = viewCategory.find("#category-id").val();
  var categoryName = viewCategory.find("#category-name").val();
  var tableBody = viewCategory.find("tbody");
  var contextPath = $(this).attr("context-path");
  tableBody.empty();
  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/searchCategory",
    data: {
      categoryId: categoryId,
      categoryName: categoryName
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data.length == 0) {
        viewCategory
          .find("#search-category-feedback")
          .text("No category found, try again!");
      }
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].categoryId))
          .append($("<td>").text(data[i].categoryName))
          .appendTo(tableBody);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function addCategory(event) {
  var addCategoryDiv = $("#add-category");
  var categoryName = addCategoryDiv.find("#category-name").val();
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addCategory",
    data: {
      categoryName: categoryName
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        addCategoryDiv
          .find("#add-category-feedback")
          .text("Add category successfully!");
        addCategoryDiv.find("#category-name").val("");
      } else {
        addCategoryDiv
          .find("#add-category-feedback")
          .text("Fail to add category!");
      }
    },
    error: function(error) {
      console.log(error);
      addCategoryDiv
        .find("#add-category-feedback")
        .text("Fail to add category!");
    }
  });
}

function searchManufacturer(event) {
  var viewManufacturer = $("#view-manufacturer");
  var manufacturerId = viewManufacturer.find("#manufacturer-id").val();
  var manufacturerName = viewManufacturer.find("#manufacturer-name").val();
  var tableBody = viewManufacturer.find("tbody");
  var contextPath = $(this).attr("context-path");
  tableBody.empty();

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/searchManufacturer",
    data: {
      manufacturerId: manufacturerId,
      manufacturerName: manufacturerName
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data.length == 0) {
        viewManufacturer
          .find("#search-manufacturer-feedback")
          .text("No manufacturer found, try again!");
      }
      for (var i = 0; i < data.length; i++) {
        $("<tr>")
          .append($("<td>").text(data[i].manufacturerId))
          .append($("<td>").text(data[i].manufacturerName))
          .appendTo(tableBody);
      }
    },
    error: function(error) {
      console.log(error);
    }
  });
}

function addManufacturer(event) {
  var addManufacturerDiv = $("#add-manufacturer");
  var manufacturerName = addManufacturerDiv.find("#manufacturer-name").val();
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/addManufacturer",
    data: {
      manufacturerName: manufacturerName
    },
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      if (data === true) {
        addManufacturerDiv
          .find("#add-manufacturer-feedback")
          .text("Add manufacturer successfully!");
        addManufacturerDiv.find("#manufacturer-name").val("");
      } else {
        addManufacturerDiv
          .find("#add-manufacturer-feedback")
          .text("Fail to add manufacturer!");
      }
    },
    error: function(error) {
      console.log(error);
      addManufacturerDiv
        .find("#add-manufacturer-feedback")
        .text("Fail to add manufacturer!");
    }
  });
}

function loadCategory(event) {
  var selectTag = $(this);
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/loadCategory",
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      selectTag.empty();
      $("#category-feedback").text("");
      for (var i = 0; i < data.length; i++) {
        selectTag.append(
          $("<option>")
            .val(data[i].categoryId)
            .text(data[i].categoryName)
        );
      }
    },
    error: function(error) {
      console.log(error);
      $("#category-feedback").text("Can't load category");
    }
  });
}

function loadManufacturer(event) {
  var selectTag = $(this);
  var contextPath = $(this).attr("context-path");

  $.ajax({
    type: "GET",
    contentType: "application/json",
    url: contextPath + "/loadManufacturer",
    dataType: "json",
    timeout: 10000,
    success: function(data) {
      selectTag.empty();
      $("#manufacturer-feedback").text("");
      for (var i = 0; i < data.length; i++) {
        selectTag.append(
          $("<option>")
            .val(data[i].manufacturerId)
            .text(data[i].manufacturerName)
        );
      }
    },
    error: function(error) {
      console.log(error);
      $("#manufacturer-feedback").text("Can't load manufacturer!");
    }
  });
}

function validateAddBook(
  categoryId,
  bookName,
  bookDescription,
  manufacturerId,
  author,
  price
) {
  var flag = true;
  if (categoryId === "") {
    flag = false;
    $("#category-id-feedback").text("Not leave this field blank!");
  } else {
    $("#category-id-feedback").text("");
  }

  if (!/.{8, 45}/.test(bookName)) {
    flag = false;
    $("#book-name-feedback").text(
      "Not leave this field blank and book name has the max size is 45 characters!"
    );
  } else {
    $("#book-name-feedback").text("");
  }

  if (bookDescription === "") {
    flag = false;
    $("#book-description-feedback").text("Not leave this field blank!");
  } else {
    $("#book-description-feedback").text("");
  }

  if (manufacturerId === "") {
    flag = false;
    $("#manufacturer-id-feedback").text("Not leave this field blank!");
  } else {
  }
}

function addBook(event) {
  event.preventDefault();
  var addBookManagement = $("#add-book-management");
  var contextPath = addBookManagement.attr("context-path");
  var categoryId = addBookManagement.find("#category-id").val();
  var bookName = addBookManagement.find("#book-name").val();
  var bookDescription = addBookManagement.find("#book-description").val();
  var manufacturerId = addBookManagement.find("#manufacturer-id").val();
  var author = addBookManagement.find("#author").val();
  var price = addBookManagement.find("#price").val();
  var size = $("#file").prop("files").length;
  if(size == 3){
    $.ajax({
      type: "GET",
      contentType: "application/json",
      url: contextPath + "/addBook",
      data: {
        categoryId: categoryId,
        bookName: bookName,
        bookDescription: bookDescription,
        manufacturerId: manufacturerId,
        author: author,
        price: price
      },
      dataType: "json",
      timeout: 10000,
      success: function(data) {
        uploadImages(data);
        $("#add-book-management-feedback").text("Add book successfully");
      },
      error: function(error) {
        $("#add-book-management-feedback").text("Cannot add this book!");
      }
    });
  }
  else {
    alert("You must uploa 3 images.");
  }
}

function uploadImages(bookId) {
  var uploadImagesForm = $("#upload-images-form");
  var fileField = $("#file").prop("files");
  var formData = new FormData();
  formData.append("bookId", bookId);

  for (var i = 0; i < fileField.length; i++) {
    formData.append("file" + i, fileField[i]);
  }

  for(var i = fileField.length; i < 3; i++){
    formData.append("file" + i, null);
  }

  var contextPath = $("#upload-images-button").attr("context-path");
  var token = $("input[name='_csrf']").val();
  var header = "X-CSRF-TOKEN";
  var headers = {};
  headers[header] = token;
  $.ajax({
    type: "POST",
    url: contextPath + "/uploadImages",
    headers: headers,
    data: formData,
    cache: false,
    enctype: "multipart/form-data",
    contentType: false,
    processData: false,
    success: function(data) {
      console.log(data);
      $("#upload-images-feedback").text("Upload images successfully!");
    },
    error: function(error) {
      console.log(error);
      $("#upload-images-feedback").text("Fail to upload images!");
    }
  });
}
