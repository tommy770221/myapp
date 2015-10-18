CREATE TABLE `oauth_access_token` (
`token_id` varchar(255) DEFAULT NULL,
`token` blob,
`authentication_id` varchar(255) NOT NULL,
`user_name` varchar(255) DEFAULT NULL,
`client_id` varchar(255) DEFAULT NULL,
`authentication` blob,
`refresh_token` varchar(255) DEFAULT NULL,
PRIMARY KEY (`authentication_id`)
) ;
CREATE TABLE `oauth_approvals` (
`userId` varchar(255) DEFAULT NULL,
`clientId` varchar(255) DEFAULT NULL,
`scope` varchar(255) DEFAULT NULL,
`status` varchar(10) DEFAULT NULL,
`expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE
CURRENT_TIMESTAMP,
`lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ;
CREATE TABLE `oauth_client_details` (
`client_id` varchar(255) NOT NULL,
`resource_ids` varchar(255) DEFAULT NULL,
`client_secret` varchar(255) DEFAULT NULL,
`scope` varchar(255) DEFAULT NULL,
`authorized_grant_types` varchar(255) DEFAULT NULL,
`web_server_redirect_uri` varchar(255) DEFAULT NULL,
`authorities` varchar(255) DEFAULT NULL,
`access_token_validity` int(11) DEFAULT NULL,
`refresh_token_validity` int(11) DEFAULT NULL,
`additional_information` text,
`autoapprove` varchar(255) DEFAULT NULL,
PRIMARY KEY (`client_id`)
) ;
CREATE TABLE `oauth_client_token` (
`token_id` varchar(255) DEFAULT NULL,
`token` blob,
`authentication_id` varchar(255) NOT NULL,
`user_name` varchar(255) DEFAULT NULL,
`client_id` varchar(255) DEFAULT NULL,
PRIMARY KEY (`authentication_id`)
) ;
CREATE TABLE `oauth_code` (
`code` varchar(255) DEFAULT NULL,
`authentication` blob
) ;
CREATE TABLE `oauth_refresh_token` (
`token_id` varchar(255) DEFAULT NULL,
`token` blob,
`authentication` blob
) ;
INSERT INTO `oauth_client_details` VALUES
('client','conference','secret','aaa,bbb,ccc','password,authorization_code,refresh_token,implicit','http://localhost:8080/foxitsign/oauthExplorer.jsp','ROLE_CLIENT',6000,6000,NULL,'false'),
('client2','conference','secret2','aaa,bbb,ccc','authorization_code','https://www.getpostman.com/oauth2/callback','ROLE_CLIENT',NULL,NULL,NULL,'false'),
('client3','conference','secret3','aaa,bbb,ccc','password,authorization_code,refresh_token,implicit',NULL,'ROLE_CLIENT',NULL,NULL,NULL,'false'),
('client4','conference','secret4','aaa,bbb,ccc','implicit',NULL,'ROLE_CLIENT',NULL,NULL,NULL,'false'),
('client5','conference','secret5','aaa,bbb,ccc','client_credentials',NULL,'ROLE_CLIENT',NULL,NULL,NULL,'false');