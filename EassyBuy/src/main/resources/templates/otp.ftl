<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>EassyBuy - Email Verification</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            background: #f3f6fb;
            font-family: Arial, Helvetica, sans-serif;
            color: #172033;
        }

        .page {
            width: 100%;
            padding: 50px 15px;
        }

        .email {
            width: 100%;
            max-width: 620px;
            margin: auto;
            background: #ffffff;
            border-radius: 18px;
            overflow: hidden;
            box-shadow: 0 12px 40px rgba(25, 42, 70, 0.10);
        }


        /* =========================
           TOP BRAND SECTION
        ========================== */

        .top {
            background: linear-gradient(
                135deg,
                #172554 0%,
                #1d4ed8 55%,
                #2563eb 100%
            );

            padding: 32px 40px 42px;
            text-align: center;
            position: relative;
        }

        .top-pattern {
            position: absolute;
            top: -80px;
            right: -80px;
            width: 200px;
            height: 200px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.08);
        }

        .top-pattern-two {
            position: absolute;
            bottom: -100px;
            left: -80px;
            width: 200px;
            height: 200px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.06);
        }

        .logo-container {
            position: relative;
            z-index: 2;
        }

        .logo {
            width: 76px;
            height: 76px;
            object-fit: contain;
            background: #ffffff;
            padding: 8px;
            border-radius: 20px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }

        .brand {
            margin: 16px 0 0;
            color: #ffffff;
            font-size: 30px;
            font-weight: 700;
            letter-spacing: -0.5px;
        }

        .tagline {
            margin: 7px 0 0;
            color: #dbeafe;
            font-size: 13px;
            letter-spacing: 0.3px;
        }


        /* =========================
           CONTENT
        ========================== */

        .content {
            padding: 45px 55px 42px;
        }

        .verification-icon {
            width: 58px;
            height: 58px;
            margin: 0 auto 22px;
            background: #eff6ff;
            border-radius: 50%;
            text-align: center;
            line-height: 58px;
            font-size: 25px;
        }

        .title {
            margin: 0;
            text-align: center;
            color: #111827;
            font-size: 27px;
            font-weight: 700;
        }

        .subtitle {
            max-width: 450px;
            margin: 13px auto 0;
            text-align: center;
            color: #6b7280;
            font-size: 14px;
            line-height: 1.7;
        }

        .hello {
            margin-top: 32px;
            color: #374151;
            font-size: 15px;
        }

        .hello strong {
            color: #111827;
        }


        /* =========================
           OTP CARD
        ========================== */

        .otp-card {
            margin-top: 24px;
            padding: 30px 25px;
            background: linear-gradient(
                135deg,
                #f8fbff,
                #eef5ff
            );

            border: 1px solid #dbeafe;
            border-radius: 14px;
            text-align: center;
        }

        .otp-heading {
            color: #64748b;
            font-size: 11px;
            font-weight: 700;
            letter-spacing: 2px;
            text-transform: uppercase;
        }

        .otp {
            margin-top: 13px;
            color: #1d4ed8;
            font-size: 40px;
            font-weight: 700;
            letter-spacing: 10px;
            padding-left: 10px;
        }

        .validity {
            margin: 15px 0 0;
            color: #64748b;
            font-size: 13px;
        }

        .validity strong {
            color: #1e40af;
        }


        /* =========================
           SECURITY
        ========================== */

        .security {
            display: table;
            width: 100%;
            margin-top: 28px;
            padding: 18px;
            background: #f8fafc;
            border: 1px solid #e5e7eb;
            border-radius: 10px;
        }

        .security-icon {
            display: table-cell;
            width: 35px;
            vertical-align: top;
            font-size: 18px;
        }

        .security-content {
            display: table-cell;
            vertical-align: top;
        }

        .security-title {
            margin: 0 0 5px;
            color: #374151;
            font-size: 13px;
            font-weight: 700;
        }

        .security-text {
            margin: 0;
            color: #6b7280;
            font-size: 12px;
            line-height: 1.6;
        }


        /* =========================
           FOOTER
        ========================== */

        .footer {
            padding: 27px 35px;
            background: #f8fafc;
            border-top: 1px solid #edf0f4;
            text-align: center;
        }

        .footer-brand {
            color: #1d4ed8;
            font-size: 14px;
            font-weight: 700;
        }

        .footer-text {
            margin: 8px 0 0;
            color: #9ca3af;
            font-size: 11px;
            line-height: 1.7;
        }


        /* =========================
           MOBILE
        ========================== */

        @media only screen and (max-width: 600px) {

            .page {
                padding: 20px 8px;
            }

            .email {
                border-radius: 14px;
            }

            .top {
                padding: 28px 20px 35px;
            }

            .content {
                padding: 35px 22px;
            }

            .brand {
                font-size: 27px;
            }

            .title {
                font-size: 23px;
            }

            .otp {
                font-size: 32px;
                letter-spacing: 7px;
                padding-left: 7px;
            }

            .footer {
                padding: 24px 20px;
            }
        }

    </style>

</head>


<body>

<div class="page">

    <div class="email">


        <!-- =========================
             HEADER
        ========================== -->

        <div class="top">

            <div class="top-pattern"></div>
            <div class="top-pattern-two"></div>

            <div class="logo-container">

                <img
                    class="logo"
                    src="https://your-domain.com/images/eassybuy-logo.png"
                    alt="EassyBuy">

                <div class="brand">
                    EassyBuy
                </div>

                <p class="tagline">
                    Your trusted shopping destination
                </p>

            </div>

        </div>


        <!-- =========================
             MAIN CONTENT
        ========================== -->

        <div class="content">

            <div class="verification-icon">
                ✉
            </div>

            <h1 class="title">
                Verify your email
            </h1>

            <p class="subtitle">
                You're almost there. Enter the verification
                code below to securely complete your EassyBuy
                account registration.
            </p>


            <p class="hello">
                Hello <strong>${name}</strong>,
            </p>


            <!-- OTP -->

            <div class="otp-card">

                <div class="otp-heading">
                    Verification Code
                </div>

                <div class="otp">
                    ${otp}
                </div>

                <p class="validity">
                    This code is valid for
                    <strong>${expiryMinutes} minutes</strong>
                </p>

            </div>


            <!-- SECURITY -->

            <div class="security">

                <div class="security-icon">
                    🔒
                </div>

                <div class="security-content">

                    <p class="security-title">
                        Keep your account secure
                    </p>

                    <p class="security-text">
                        Never share this verification code with anyone.
                        EassyBuy will never ask you for your OTP by
                        phone, email, or message.
                    </p>

                </div>

            </div>

        </div>


        <!-- =========================
             FOOTER
        ========================== -->

        <div class="footer">

            <div class="footer-brand">
                EassyBuy
            </div>

            <p class="footer-text">

                This is an automated email sent by EassyBuy.
                Please do not reply to this message.

                <br>

                © 2026 EassyBuy. All rights reserved.

            </p>

        </div>


    </div>

</div>

</body>

</html>