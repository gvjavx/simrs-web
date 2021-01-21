select * from im_users where user_id ILIKE '%tek%'

select * from it_akun_jurnal
select * from it_akun_jurnal_detail
select * from it_akun_jurnal_detail_activity

select a.no_jurnal,
b.
from it_akun_jurnal a
inner join it_akun_jurnal_detail b on a.no_jurnal = b.no_jurnal
inner join it_akun_jurnal_detail_activity c on b.jurnal_detail_id = c.jurnal_detail_id
where a.no_jurnal ='210101589'

