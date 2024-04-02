<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        p {
            color: #666;
        }
        .content {
            margin-top: 20px;
            line-height: 1.5;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${title}</h1>
        <p>Date: ${date}</p>
        <hr>
        <div class="content">${content}</div>
    </div>
</body>
</html>
