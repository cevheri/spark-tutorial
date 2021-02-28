-- cassandra create table
create table flowinformation
(
    starttime               text primary key,
    applicationinstanceip   text,
    applicationinstancename text,
    applicationversion      text,
    classname               text,
    endtime                 text,
    exceptionmessage        text,
    exceptionstacktrace     text,
    exceptiontype           text,
    exitcode                text,
    externaldata            text,
    methodname              text,
    requesttype             text,
    url                     text,
    userloginname           text,
    usersessionid           text
)
with caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
     and compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
     and compression = {'chunk_length_in_kb': '16', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
     and dclocal_read_repair_chance = 0.0
     and default_time_to_live = 3888000
     and speculative_retry = '99p';