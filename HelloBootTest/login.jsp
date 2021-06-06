<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Login Form</title>

    <!-- Icons font CSS-->
    <link href="/HelloBootTest/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="/HelloBootTest/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="/HelloBootTest/resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/HelloBootTest/resources/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="/HelloBootTest/resources/css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-custom p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                    <h2 class="title">Login</h2>
                </div>
                <div class="card-body" style='text-align:center;'>
			<form action='login' method='get'>
			<h3 style='color:red;'>${error}</h3>
                        <div class="form-row">
                            <div class="name">Username</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="username">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Password</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="password">
                                </div>
                            </div>
                        </div>

                        <div>
                            <button class="btn btn--radius-1" style="color:white;background:black;width:200px;" type="submit">Login</button>&emsp;&emsp;&emsp;
			    <a href='index'><button class="btn btn--radius-1" style="color:white;background:black;" type="button">IndexPage</button></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="/HelloBootTest/resources/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="/HelloBootTest/resources/vendor/select2/select2.min.js"></script>
    <script src="/HelloBootTest/resources/vendor/datepicker/moment.min.js"></script>
    <script src="/HelloBootTest/resources/vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="/HelloBootTest/resources/js/global.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->