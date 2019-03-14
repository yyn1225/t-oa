/*
Navicat SQL Server Data Transfer

Source Server         : localhost_1433
Source Server Version : 105000
Source Host           : localhost:1433
Source Database       : XBTest2
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2018-08-08 20:13:23
*/


-- ----------------------------
-- Table structure for absen_experience_share
-- ----------------------------
DROP TABLE [dbo].[absen_experience_share]
GO
CREATE TABLE [dbo].[absen_experience_share] (
[id] bigint NOT NULL ,
[series_id] int NOT NULL ,
[title] varchar(300) NOT NULL ,
[content] varchar(2000) NOT NULL ,
[status] smallint NOT NULL ,
[create_user] int NOT NULL ,
[create_time] datetime NOT NULL ,
[modify_user] int NULL ,
[modify_time] datetime NULL 
)


GO

-- ----------------------------
-- Indexes structure for table absen_experience_share
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table absen_experience_share
-- ----------------------------
ALTER TABLE [dbo].[absen_experience_share] ADD PRIMARY KEY ([id])
GO
