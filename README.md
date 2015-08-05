# SEQR - SEQuence Retreival Tool

## Super-fast protein sequence similarity search, based on [Solr](http://lucene.apache.org/solr/).

The underlying concept of the project is that we have developed an algorithm, SEQR, to find similar protein sequences to a query protein sequence by using indexes.  This makes it very fast to retrieve similar sequences and easy to combine with other indices like taxonomy.  What we'd like to do for the initial goal of the hackathon is to make a command line version of this algorithm as it should be useful in many types of bioinformatics data pipelines, in a similar fashion to how BLAST and eutils are used. 
 
Another goal is to easily index any sequence related data and allow command line searching of that data.  To that end, we have partnered with the RNASeq mapping team to allow indexing of VCF files.


### License

SEQR is released under the terms of [Creative Commons 0: Public Domain](https://github.com/DCGenomics/seqr_hackathon_v002/blob/master/LICENSE).

### Solr

SEQR requires a Solr backend, but it'll instantiate it's own embedded Solr server if you don't have one running. Either way, you'll need to provide either the URL of a running Solr instance, or a path to a directory where the embedded Solr can build and save indexes. Supply this as an argument to the `-d` option:

```
$ seqr search -d /path/to/solr/directory/ <fasta_file>
$ seqr index -d http://a.solr.server/ <fasta_file>
```

### usage
```
usage: seqr [-h] -d SOLR_URL_OR_PATH [--version] [--citation]
            [-o OUTPUT_FILE] [--parse_deflines] [--html] [--lcase_masking]
            [--remote] [--show_gis] [--ungapped] [--use_sw_tback]
            [--import_search_strategy FILENAME]
            [--export_search_strategy FILENAME] [--task TASK_NAME]
            [--dbsize NUM_LETTERS] [--gilist FILENAME]
            [--seqidlist FILENAME] [--negative_gilist FILENAME]
            [--entrez_query ENTREZ_QUERY]
            [--db_soft_mask FILTERING_ALGORITHM]
            [--db_hard_mask FILTERING_ALGORITHM]
            [--subject SUBJECT_INPUT_FILE] [--subject_loc RANGE]
            [--evalue EVALUE] [--word_size WORD_SIZE]
            [--gapopen OPEN_PENALTY] [--gapextend EXTEND_PENALTY]
            [--qcov_hsp_perc QCOV_HSP_PERC] [--max_hsps MAX_HSPS]
            [--xdrop_ungap XDROP_UNGAP] [--xdrop_gap XDROP_GAP]
            [--xdrop_gap_final XDROP_GAP_FINAL] [--searchsp SEARCHSP]
            [--sum_stats BOOL] [--seg SEG_OPTIONS]
            [--soft_masking SOFT_MASKING] [--matrix MATRIX_NAME]
            [--threshold THRESHOLD] [--culling_limit CULLING_LIMIT]
            [--best_hit_overhang BEST_HIT_OVERHANG]
            [--best_hit_score_edge BEST_HIT_SCORE_EDGE]
            [--window_size WINDOW_SIZE] [--query_loc RANGE]
            [--num_descriptions NUM_DESCRIPTIONS]
            [--line_length LINE_LENGTH] [--max_target_seqs NUM_SEQUENCES]
            [--num_threads NUM_THREADS] [--comp_based_stats COMPO] COMMAND
            ...

SEQR, now for shells.

optional arguments:
  -h, --help             show this help message and exit
  -d SOLR_URL_OR_PATH, --db SOLR_URL_OR_PATH
                         URL of Solr server, or path to Solr directory
  --version
  --citation             (default: false)
  -o OUTPUT_FILE, --out OUTPUT_FILE
                         path to output directory
  --parse_deflines       derive metadata from  FASTA  deflines and comments
                         (default: false)

subcommands:
  SEQR commands

  COMMAND
    search               look for something
    index                create an index

unused:
  Unused compatibility arguments from BLASTP

  --html                 unused (default: false)
  --lcase_masking        unused (default: false)
  --remote               unused (default: false)
  --show_gis             unused (default: false)
  --ungapped             unused (default: false)
  --use_sw_tback         unused (default: false)
  --import_search_strategy FILENAME
                         unused
  --export_search_strategy FILENAME
                         unused
  --task TASK_NAME       unused
  --dbsize NUM_LETTERS   unused
  --gilist FILENAME      unused
  --seqidlist FILENAME   unused
  --negative_gilist FILENAME
                         unused
  --entrez_query ENTREZ_QUERY
                         unused
  --db_soft_mask FILTERING_ALGORITHM
                         unused
  --db_hard_mask FILTERING_ALGORITHM
                         unused
  --subject SUBJECT_INPUT_FILE
                         unused
  --subject_loc RANGE    unused
  --evalue EVALUE        unused
  --word_size WORD_SIZE  unused
  --gapopen OPEN_PENALTY
                         unused
  --gapextend EXTEND_PENALTY
                         unused
  --qcov_hsp_perc QCOV_HSP_PERC
                         unused
  --max_hsps MAX_HSPS    unused
  --xdrop_ungap XDROP_UNGAP
                         unused
  --xdrop_gap XDROP_GAP  unused
  --xdrop_gap_final XDROP_GAP_FINAL
                         unused
  --searchsp SEARCHSP    unused
  --sum_stats BOOL       unused
  --seg SEG_OPTIONS      unused
  --soft_masking SOFT_MASKING
                         unused
  --matrix MATRIX_NAME   unused
  --threshold THRESHOLD  unused
  --culling_limit CULLING_LIMIT
                         unused
  --best_hit_overhang BEST_HIT_OVERHANG
                         unused
  --best_hit_score_edge BEST_HIT_SCORE_EDGE
                         unused
  --window_size WINDOW_SIZE
                         unused
  --query_loc RANGE      unused
  --num_descriptions NUM_DESCRIPTIONS
                         unused
  --line_length LINE_LENGTH
                         unused
  --max_target_seqs NUM_SEQUENCES
                         unused
  --num_threads NUM_THREADS
                         unused
  --comp_based_stats COMPO
                         unused
```
