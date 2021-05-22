This class can be used in situations where the full index on an Alfresco repository failed to index some nodes.

To prepare for its use, you need to have an export of all the DBIDs that need to be indexed (it could be all of them) in a text file, one DBID per line.

If you download it, compile it and run it, it will ask you what kind of action you would like to perform (INDEX, REINDEX, FIX or PURGE) then the name of the host (e.g http://localhost:8983) then the name of the Solr core to hit (e.g alfresco) and finally the path to the file containing the list of nodes
(e.g C:\tmp\node_list.txt).

Once you enter the parameters and accept, it will index the nodes one by one. With the current version, you can find a log file at C:\tmp\log.log.

Please adjust the paths for Unix machines.
