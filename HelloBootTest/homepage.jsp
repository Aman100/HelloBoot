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
    <title>Employee Details</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


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
                    <h2 class="title">Employee Details</h2>
                </div>
                <div class="card-body">
                        <div class="form-row">
                            <div class="name">Name</div>
                            <div class="value">
                                <div class="input-group">
                                    ${employee.name}
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Age</div>
                            <div class="value">
                                <div class="input-group">
                                    ${employee.age}
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="name">Gender</div>
                            <div class="value">
                                <div class="input-group">
                                    ${employee.gender}
                                </div>
                            </div>
                        </div>


                        <div>
			    <a href='update'>UpdateEmployee</a>&emsp;
                            <a href='view' target="_blank">View PDF</a>&emsp;
			    <a href='getEmployee' target="_blank">GET EMPLOYEE AS JSON</a>&emsp;
			    <a href='logout'>Logout</a>&emsp;
                        </div>
                </div>
            </div>
        </div>
    </div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


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