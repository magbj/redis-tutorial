import redis
from redis import WatchError

r = redis.StrictRedis(host='localhost', port=6379, db=0)
r.set('foo', 'bar')
value=r.get('foo')

print 'result: '+value

with r.pipeline() as pipe:
	try:
		pipe.watch('MYKEY')
		current_value = pipe.get('MYKEY')
		next_value = int(current_value) + 1
		pipe.multi()
		pipe.set('MYKEY', next_value)
		pipe.execute()
	except WatchError:
		# another client must have changed 'MYKEY' between
		# the time we started WATCHing it and the transaction's execution.
		None
