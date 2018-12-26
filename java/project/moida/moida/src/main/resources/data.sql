INSERT INTO member (name, fbLink, blogLink) VALUES ('bbd', 'brainbackdoor', 'www.tistory.com/brainbackdoor');


INSERT INTO form (memberId, createdDate) VALUES (1, now());

INSERT INTO item (formId, content) VALUES (1, '두괄식으로 써야 한다.');
INSERT INTO item (formId, content) VALUES (1, '한 문단은 반드시 한 가지 생각만을 담아야 한다.');
INSERT INTO item (formId, content) VALUES (1, '접속사를 적극적으로 활용한다.');
INSERT INTO item (formId, content) VALUES (1, '가능하면 "그것, 이것, 저것" 등의 대명사를 사용하지 않는다.');
INSERT INTO item (formId, content) VALUES (1, '가능한 문장은 짧게 써야 한다.');
