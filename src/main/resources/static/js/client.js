/**
 * Created by stephan on 20.03.16.
 */

$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"
    var $notLoggedIn = $("#notLoggedIn");
    var $loggedIn = $("#loggedIn").hide();
    var $response = $("#response");
    var $login = $("#login");
    var $userInfo = $("#userInfo").hide();

    var all_items = $("#all_items");
    var all_auctions = $("#all_auctions");
    var all_bids = $("#all_bids");
    // FUNCTIONS =============================================================


// USER DETAILS =========================

$("#get_user_details").click(function() {
  $.ajax({
    url: "/api/users/details",
    type: "GET",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    headers: createAuthorizationTokenHeader(),
    success: function(data, textStatus, jqXHR) {
      var user_id = data.id;
      $("#address").val(data.address);
      $("#email").val(data.email);
      $("#phone").val(data.phone);
      $("#img").attr("src", data.picture);

      $("#upload-userPic-input").on("change", uploadUserPic);
      function uploadUserPic() {
        $.ajax({
            url: "/uploadFile",
            type: "POST",
            data: new FormData($("#upload-userPic-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            headers: createAuthorizationTokenHeader(),
            success: function(data) {
              $("#send_changes").click(function() {
                var edit_details = {
                  "address": $("#address").val(),
                  "email": $("#email").val(),
                  "picture": data,
                  "phone": $("#phone").val()
                };

                $.ajax({
                  url: "/api/users/"+user_id,
                  type: "PUT",
                  data: JSON.stringify(edit_details),
                  contentType: "application/json; charset=utf-8",
                  dataType: "json",
                  headers: createAuthorizationTokenHeader(),
                  success: function(data, textStatus, jqXHR) {
                     console.log("success!");
                  },
                  error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR "+ textStatus);
                  }
                });

            });
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.log("ERROR");
          }
        });
      }


    },
    error: function(jqXHR, textStatus, errorThrown) {
      showResponse(jqXHR.status, errorThrown);
    }
  });
});



// /////// AUCTIONS======================

$("#add_new_auction").click(function() {
  $("#add_auction_window").modal("show");

  $("#add_auction").click(function() {
    var new_auction = {
      "item_id": $("#item_id").val(),
      "startDate": $("#start_date").val()+ " 00:00:00",
      "endDate": $("#end_date").val() + " 00:00:00",
      "startPrice": $("#start_price").val()
    };

    $.ajax({
      url: "/api/auctions",
      type: "POST",
      data: JSON.stringify(new_auction),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      headers: createAuthorizationTokenHeader(),
      success: function(data, textStatus, jqXHR) {
         window.location.replace("/");
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.log("ERROR "+textStatus);
      }
    });

});
});

all_auctions.on('click', 'input.edit_auction', function(e) {
  $("#edit_auction_window").modal("show");
  var auction_id = $(this).attr('auction_id');
  var row = $(this).closest('tr');
  var sd_el = row.find(".startDate");
  var startDate = sd_el.text();
  var ed_el = row.find(".endDate");
  var endDate = ed_el.text();
  var sp_el = row.find(".startPrice");
  var startPrice = sp_el.text();
  $("#edit_start_date").val(startDate);
  $("#edit_end_date").val(endDate);
  $("#edit_start_price").val(startPrice);

  $("#edit_auction").click(function() {
    var edit_auction = {
      "startDate": $("#edit_start_date").val(),
      "endDate": $("#edit_end_date").val(),
      "startPrice": $("#edit_start_price").val()
    };

    $.ajax({
      url: "/api/auctions/"+auction_id,
      type: "PUT",
      data: JSON.stringify(edit_auction),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      headers: createAuthorizationTokenHeader(),
      success: function(data, textStatus, jqXHR) {
         window.location.replace("/");
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.log("ERROR "+ textStatus);
      }
    });

});
});

all_auctions.on('click', 'input.add_bid', function(e) {
  var auction_id = $(this).attr('auction_id');
  var row = $(this).closest('tr');
  var sp_el = row.find(".startPrice");
  var startPrice = sp_el.text();
  var bid_price = parseFloat(startPrice);
    var add_bid = {
      "auction_id": auction_id,
      "price": bid_price
    };

    console.log(add_bid);

    $.ajax({
      url: "/api/bids",
      type: "POST",
      data: JSON.stringify(add_bid),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      headers: createAuthorizationTokenHeader(),
      success: function(data, textStatus, jqXHR) {
         console.log("successfuly bid " + data);
      },
      error: function(jqXHR, textStatus, errorThrown) {
        console.log("ERROR "+ textStatus);
      }
    });

});




all_auctions.on('click', 'input.delete_auction', function(e) {
    e.preventDefault();
    if (confirm("Are you sure you want to delete this auction?")) {
      var auction_id = $(this).attr('auction_id');
      var row = $(this).closest('tr'); //najblizi njemu jer taj tr sadrzi bas taj input

      $.ajax({
        url: "/api/auctions/"+auction_id,
        type: "DELETE",
        headers: createAuthorizationTokenHeader(),
        success: function(data, textStatus, jqXHR) {
          row.remove();
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log("ERROR" + textStatus);
        }
      });
    }
    });


    all_auctions.on('click', 'input.show_bids', function(e) {
      $("#show_bids_window").modal("show");
      $.ajax({
        url: "/api/bids",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        headers: createAuthorizationTokenHeader(),
        success: function(data, textStatus, jqXHR) {
          all_bids.find('tr:gt(0)').remove();
          for (var i = 0; i < data.length; i++) {
            appendBid(data[i]);
          }
        },
        error: function(jqXHR, textStatus, errorThrown) {
          showResponse(jqXHR.status, errorThrown);
        }
      });
    });


    function appendBid(bid) {
      all_bids.append(
        '<tr>' +
        '  <td class="">' + bid.date + '</td>' +
        '  <td class="">' + bid.price + '</td>' +
        '  <td class="">' + bid.user_id + '</td>' +
        '<td> ' + bid.auction_id + '  </td>' +
        '</tr>'
      );
    }


var all_auctions = $("#all_auctions");
$("#get_all_auctions").click(function() {
  $.ajax({
    url: "/api/auctions",
    type: "GET",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    headers: createAuthorizationTokenHeader(),
    success: function(data, textStatus, jqXHR) {
      all_auctions.find('tr:gt(0)').remove();
      for (var i = 0; i < data.length; i++) {
        appendAuction(data[i]);
      }
    },
    error: function(jqXHR, textStatus, errorThrown) {
      showResponse(jqXHR.status, errorThrown);
    }
  });
});

$("#get_my_auctions").click(function() {
  $.ajax({
    url: "/api/auctions/my",
    type: "GET",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    headers: createAuthorizationTokenHeader(),
    success: function(data, textStatus, jqXHR) {
      all_auctions.find('tr:gt(0)').remove();
      for (var i = 0; i < data.length; i++) {
        appendAuction(data[i]);
      }
    },
    error: function(jqXHR, textStatus, errorThrown) {
      showResponse(jqXHR.status, errorThrown);
    }
  });
});

$("#get_my_items").click(function() {
  $.ajax({
    url: "/api/items/my",
    type: "GET",
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    headers: createAuthorizationTokenHeader(),
    success: function(data, textStatus, jqXHR) {
      all_items.find('tr:gt(0)').remove();
      for (var i = 0; i < data.length; i++) {
        appendItem(data[i]);
      }
    },
    error: function(jqXHR, textStatus, errorThrown) {
      showResponse(jqXHR.status, errorThrown);
    }
  });
});


function appendAuction(auction) {
  all_auctions.append(
    '<tr>' +
    '  <td class="startDate">' + auction.startDate + '</td>' +
    '  <td class="endDate">' + auction.endDate + '</td>' +
    '  <td class="startPrice">' + auction.startPrice + '</td>' +
    '<td> <img src="' + auction.item_pic + '" alt="pi" style="height:60px; width:60px;"> </td>' +
    '<td> ' + auction.owner + '  </td>' +
    '<td>' +
    '<input type="button" class="btn btn-default show_bids" value="show bids"' +
    'auction_id="' + auction.auction_id + '">' +
    '<input type="button" class="btn btn-default add_bid" value="add bid"' +
    'auction_id="' + auction.auction_id + '">' +
    '<input type="button" class="btn btn-default edit_auction" value="edit"' +
    'auction_id="' + auction.auction_id + '">' +
    '<input class="btn btn-default delete_auction" type="button" value="delete"' +
    'auction_id="' + auction.auction_id + '">' +
    '  </td>' +
    '</tr>'
  );
}

//////////////////////ITEMS===================================
    $("#add_new_item").click(function() {
      $("#add_item_window").modal("show");
      $("#upload-file-input").on("change", uploadFile);
    });

    all_items.on('click', 'input.edit_item', function(e) {
      $("#edit_item_window").modal("show");
      var item_id = $(this).attr('item_id');
      var row = $(this).closest('tr');
      var n_el = row.find(".name");
      var name = n_el.text();
      var d_el = row.find(".desc");
      var desc = d_el.text();
      $("#edit_item_name").val(name);
      $("#edit_item_desc").val(desc);
      $("#edit_upload-file-input").on("change", uploadFileEdit);


      function uploadFileEdit() {
        $.ajax({
            url: "/uploadFile",
            type: "POST",
            data: new FormData($("#edit_upload-file-form")[0]),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            headers: createAuthorizationTokenHeader(),
            success: function(data) {
              $("#edit_item").click(function() {
                var edit_item = {
                  "name": $("#edit_item_name").val(),
                  "description": $("#edit_item_desc").val(),
                  "picture": data
                };

                $.ajax({
                  url: "/api/items/"+item_id,
                  type: "PUT",
                  data: JSON.stringify(edit_item),
                  contentType: "application/json; charset=utf-8",
                  dataType: "json",
                  headers: createAuthorizationTokenHeader(),
                  success: function(data, textStatus, jqXHR) {
                     window.location.replace("/");
                  },
                  error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR");
                  }
                });

            });
          },
          error: function(jqXHR, textStatus, errorThrown) {
            console.log("ERROR"+ textStatus);
          }
        });
      }
    });




    function uploadFile() {
      $.ajax({
          url: "/uploadFile",
          type: "POST",
          data: new FormData($("#upload-file-form")[0]),
          enctype: 'multipart/form-data',
          processData: false,
          contentType: false,
          cache: false,
          headers: createAuthorizationTokenHeader(),
          success: function(data) {
            $("#add_item").click(function() {
              var new_item = {
                "name": $("#item_name").val(),
                "description": $("#item_desc").val(),
                "picture": data
              };

              $.ajax({
                url: "/api/items",
                type: "POST",
                data: JSON.stringify(new_item),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                headers: createAuthorizationTokenHeader(),
                success: function(data, textStatus, jqXHR) {
                   window.location.replace("/");
                },
                error: function(jqXHR, textStatus, errorThrown) {
                  console.log("ERROR");
                }
              });

          });
        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log("ERROR");
        }
      });
    } // function uploadFile


    $("#get_all_items").click(function () {
        $.ajax({
            url: "/api/items",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
              all_items.find('tr:gt(0)').remove();
              for (var i = 0; i < data.length; i++) {
                appendItem(data[i]);
          }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    function appendItem(item) {
        all_items.append(
          '<tr>' +
          '  <td class="name">' + item.id + '</td>' +
          '<td> <img src="' + item.picture + '" alt="pi" style="height:60px; width:60px;"> </td>' +
          '  <td class="name">' + item.name + '</td>' +
          '  <td class="desc">' + item.description + '</td>' +
          '  <td class="owner">' + item.owner + '</td>' +
          '<td> ' + item.sold + '  </td>' +
          '<td>' +
          '<input type="button" class="btn btn-default edit_item" value="edit"' +
          'item_id="' + item.id + '">' +
          '<input class="btn btn-default delete_item" type="button" value="delete"' +
          'item_id="' + item.id + '">' +
          '  </td>' +
          '</tr>'
        );
      }


      all_items.on('click', 'input.delete_item', function(e) {
          e.preventDefault();
          if (confirm("Are you sure you want to delete this item?")) {
            var item_id = $(this).attr('item_id');
            var row = $(this).closest('tr'); //najblizi njemu jer taj tr sadrzi bas taj input

            $.ajax({
              url: "/api/items/"+item_id,
              type: "DELETE",
              headers: createAuthorizationTokenHeader(),
              success: function(data, textStatus, jqXHR) {
                row.remove();
              },
              error: function(jqXHR, textStatus, errorThrown) {
                console.log("ERROR" + textStatus);
              }
            });
          }
          });

    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                setJwtToken(data.token);
                $login.hide();
                $notLoggedIn.hide();
                showTokenInformation()
                showUserInformation();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    $('#loginErrorModal')
                        .modal("show")
                        .find(".modal-body")
                        .empty()
                        .html("<p>Spring exception:<br>" + jqXHR.responseJSON.exception + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    function doLogout() {
        removeJwtToken();
        $login.show();
        $userInfo
            .hide()
            .find("#userInfoBody").empty();
        $loggedIn
            .hide()
            .attr("title", "")
            .empty();
        $notLoggedIn.show();
    }

    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

    function showUserInformation() {
        $.ajax({
            url: "/user",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                var $userInfoBody = $userInfo.find("#userInfoBody");

                $userInfoBody.append($("<div>").text("Username: " + data.username));
                $userInfoBody.append($("<div>").text("Email: " + data.email));

                var $authorityList = $("<ul>");
                data.authorities.forEach(function (authorityItem) {
                    $authorityList.append($("<li>").text(authorityItem.authority));
                });
                var $authorities = $("<div>").text("Authorities:");
                $authorities.append($authorityList);

                $userInfoBody.append($authorities);
                $userInfo.show();
            }
        });
    }

    function showTokenInformation() {
        $loggedIn
            .text("Token: " + getJwtToken())
            .attr("title", "Token: " + getJwtToken())
            .show();
    }

    function showResponse(statusCode, message) {
        $response
            .empty()
            .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    $("#logoutButton").click(doLogout);

    $("#exampleServiceBtn").click(function () {
        $.ajax({
            url: "/persons",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, JSON.stringify(data));
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $("#adminServiceBtn").click(function () {
        $.ajax({
            url: "/protected",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $loggedIn.click(function () {
        $loggedIn
                .toggleClass("text-hidden")
                .toggleClass("text-shown");
    });

    // INITIAL CALLS =============================================================
    if (getJwtToken()) {
        $login.hide();
        $notLoggedIn.hide();
        showTokenInformation();
        showUserInformation();
    }
});
