# Sewa-Security

# Nginx
sudo apt-get install nginx
sudo apt update;


/etc/nginx/sites-enabled$ cat default

server {
	listen 81 default_server;
	listen [::]:81 default_server;
	root /home/gaian/Videos/content-data;
	index index.html index.htm index.nginx-debian.html;
	server_name patel.mossad.com;
	location / {
		# First attempt to serve request as file, then
		# as directory, then fall back to displaying a 404.
		try_files $uri $uri/ =404;
	}	
}


index.html

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello, Nginx!</title>
</head>
<body>
    <h1>Hello, Nginx!</h1>
    <p>We have just configured our Nginx web server on Ubuntu Server!</p>
</body>
</html>
