package gov.nih.nlm.ncbi.seqr;

import gov.nih.nlm.ncbi.seqr.solr.FastaStreamParser;
import gov.nih.nlm.ncbi.seqr.solr.LoadLargeFile2SolrServer;
import gov.nih.nlm.ncbi.seqr.solr.SeqrController;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.core.CoreContainer;
import java.io.File;

//import org.codehaus.jackson.JsonNode;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestMain {
    private SeqrController control;
    EmbeddedSolrServer solrServer;
CoreContainer container ;
    public void setUp() throws SolrServerException, InterruptedException, IOException {
        String solrString = "testdata/solr";
        container = new CoreContainer(solrString);
        container.load();
        solrServer = new EmbeddedSolrServer(container, "sequence");
        control = new SeqrController(solrServer);
        control.loadJSONDir("testdata/data");

        LoadLargeFile2SolrServer server = new LoadLargeFile2SolrServer(solrServer);
        server.loadFastaFile(new File("testdata/data/short.fasta"));

    }

    public void tearDown() {
        solrServer.shutdown();
        container.shutdown();
    }

    @Test
    public void testFastaSolrImport() throws Exception {
        LoadLargeFile2SolrServer server = new LoadLargeFile2SolrServer(solrServer);
        server.loadFastaFile(new File("testdata/data/short.fasta"));

    }

    @Test
    public void testAbSolrSimple() throws Exception {
        setUp();
        //String seq = "MAFSAEDVLKEYDRRRRMEALLLSLYYPNDRKLLDYKEWSPPRVQVECPKAPVEWNNPPSEKGLIVGHFSGIKYKGEKAQASEVDVNKMCCWVSKFKDAMRRYQGIQTCKIPGKVLSDLDAKIKAYNLTVEGVEGFVRYSRVTKQHVAAFLKELRHSKQYENVNLIHYILTDKRVDIQHLEKDLVKDFKALVESAHRMRQGHMINVKYILYQLLKKHGHGPDGPDILTVKTGSKGVLYDDSFRKIYTDLGWKFTPL";
        String seq = "MSFKVYDPIAELIATQFPTSNPDLQIINNDVLVVSPHKITLPMGPQNAGDVTNKAYVDQAVMSAAVPVASSTTVGTIQMAGDLEGSSGTNPIIAANKITLNKLQKIGPKMVIGNPNSDWNNTQEIELDSSFRIVDNRLNAGIVPISSTDPNKSNTVIPAPQQNGLFYLDSSGRVWVWAEHYYKCITPSRYISKWMGVGDFQELTVGQSVMWDSGRPSIETVSTQGLEVEWISSTNFTLSSLYLIPIVVKVTICIPLLGQPDQMAKFVLYSVSSAQQPRTGIVLTTDSSRSSAPIVSEYITVNWFEPKSYSVQLKEVNSDSGTTVTICSDKWLANPFLDCWITIEEVG";
        //String seq = "AGSYLLEELFEGHLEKECWEEICVYEEAREVFEDDETTDEFWRTYMGGSPCASQPCLNNGSCQDSIRGYACTCAPGYEGPNCAFAESECHPLRLDGCQHFCYPGPESYTCSCARGHKLGQDRRSCLPHDRCACGTLGPECCQRPQGSQQNLLPFPWQVKLTNSEGKDFCGGVLIQDNFVLTTATCSLLYANISVKTRSHFRLHVRGVHVHTRFEADTGHNDVALLDLARPVRCPDAGRPVCTADADFADSVLLPQPGVLGGWTLRGREMVPLRLRVTHVEPAECGRALNATVTTRTSCERGAAAGAARWVAGGAVVREHRGAWFLTGLLGAAPPEGPGPLLLIKVPRYALWLRQVTQQPSRASPRGDRGQGRDGEPVPGDRGGRWAPTALPPGPLV";
        SolrDocumentList results = control.search(seq);
        for (SolrDocument document : results) {
            System.out.println(document);
        }
        Assert.assertEquals(1, results.size());
        solrServer.shutdown();
    }

    @Test
    public void testArgParse() {
        String[] args = {"search",  "testdata/data/test.fasta", "--db", "testdata/solr/"};
        Seqr.main(args);

        }

    @Test
    public void testFastaToJsonFileConversion() throws Exception {
        FastaStreamParser converter = new FastaStreamParser("testdata/data/short.fasta");
        converter.convertToJsonFile("testdata/data/short.json");
    }


}