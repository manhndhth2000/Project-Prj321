USE [master]
GO

/****** Object:  Database [Project_Prj321]    Script Date: 7/26/2021 2:53:50 PM ******/
CREATE DATABASE [Project_Prj321]

USE [Project_Prj321]
GO

/****** Object:  Table [dbo].[tblAdmin]    Script Date: 7/26/2021 2:54:49 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tblAdmin](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[dob] [date] NULL,
	[information] [text] NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[image] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblAdmin] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

CREATE TABLE [dbo].[tblBlogPost](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category_id] [int] NOT NULL,
	[author_id] [int] NOT NULL,
	[title] [varchar](100) NOT NULL,
	[description] [text] NOT NULL,
	[image] [nvarchar](50) NULL,
	[timePost] [datetime2](0) NOT NULL,
	[shortDes] [text] NOT NULL,
 CONSTRAINT [PK_tblBlogPost] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblBlogPost]  WITH CHECK ADD  CONSTRAINT [FK_tblBlogPost_tblAdmin] FOREIGN KEY([author_id])
REFERENCES [dbo].[tblAdmin] ([id])
GO

ALTER TABLE [dbo].[tblBlogPost] CHECK CONSTRAINT [FK_tblBlogPost_tblAdmin]
GO

ALTER TABLE [dbo].[tblBlogPost]  WITH CHECK ADD  CONSTRAINT [FK_tblBlogPost_tblCategory] FOREIGN KEY([category_id])
REFERENCES [dbo].[tblCategory] ([id])
GO

ALTER TABLE [dbo].[tblBlogPost] CHECK CONSTRAINT [FK_tblBlogPost_tblCategory]
GO

CREATE TABLE [dbo].[tblCategory](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[shortDescription] [text] NOT NULL,
 CONSTRAINT [PK_tblCategory] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

CREATE TABLE [dbo].[tblComment](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NOT NULL,
	[blog_post_id] [int] NOT NULL,
	[timePost] [datetime2](0) NOT NULL,
	[description] [text] NOT NULL,
	[like] [int] NULL,
 CONSTRAINT [PK_tblComment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK_tblComment_tblBlogPost] FOREIGN KEY([blog_post_id])
REFERENCES [dbo].[tblBlogPost] ([id])
GO

ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK_tblComment_tblBlogPost]
GO

ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK_tblComment_tblUser] FOREIGN KEY([user_id])
REFERENCES [dbo].[tblUser] ([id])
GO

ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK_tblComment_tblUser]
GO

CREATE TABLE [dbo].[tblUser](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[gender] [nvarchar](50) NULL,
	[address] [varchar](100) NULL,
	[dateofbirth] [date] NULL,
	[image] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[LikeAndUnlike](
	[cmtId] [int] NOT NULL,
	[user] [int] NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[LikeAndUnlike]  WITH CHECK ADD  CONSTRAINT [FK_LikeAndUnlike_tblComment] FOREIGN KEY([cmtId])
REFERENCES [dbo].[tblComment] ([id])
GO

ALTER TABLE [dbo].[LikeAndUnlike] CHECK CONSTRAINT [FK_LikeAndUnlike_tblComment]
GO

ALTER TABLE [dbo].[LikeAndUnlike]  WITH CHECK ADD  CONSTRAINT [FK_LikeAndUnlike_tblUser] FOREIGN KEY([user])
REFERENCES [dbo].[tblUser] ([id])
GO

ALTER TABLE [dbo].[LikeAndUnlike] CHECK CONSTRAINT [FK_LikeAndUnlike_tblUser]
GO

