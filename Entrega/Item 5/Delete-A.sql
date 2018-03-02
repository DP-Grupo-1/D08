start transaction;


revoke all privileges on `Acme-Rendezvous`.*from 'acme-user'@'%';
revoke all privileges on `Acme-Rendezvous`.*from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-Rendezvous`;

commit;