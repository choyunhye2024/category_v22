use my_cat;

CREATE TABLE PS_BOARD_FREE (
		B_NO INT PRIMARY KEY AUTO_INCREMENT, 			#글번호
		B_TITLE CHAR(100) NOT NULL DEFAULT "",			#글제목
	    B_ID CHAR(50) NOT NULL,							#작성자ID
		B_DATETIME DATETIME NOT NULL DEFAULT now(),		#작성시간
	    B_HIT INT NOT NULL DEFAULT 0,					#조회수    
	    B_TEXT TEXT	NOT NULL,							#글내용, 댓글내용
	    B_REPLY_COUNT INT NOT NULL DEFAULT 0,			#댓글수
	    B_REPLY_ORI INT	NOT NULL DEFAULT -1				#댓글의 원글 번호
	);
drop table ps_board_free;    
insert into ps_board_free (b_title,b_id,b_text) values ('야옹','cat','aaa');
select * from ps_board_free;
select * from ps_board_free where b_no=4;
delete from ps_board_free where b_no=1;
update ps_board_free set b_title='bb',b_text='bbbb' where b_no=4;
select * from ps_board_free where b_no=2;
desc ps_board_free;

#게시판에 카테고리 칼럼 추가함
ALTER TABLE PS_BOARD_FREE ADD B_CATEGORY CHAR(50);    
UPDATE PS_BOARD_FREE SET B_CATEGORY = "free";

