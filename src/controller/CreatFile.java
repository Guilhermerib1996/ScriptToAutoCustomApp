package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


public class CreatFile {
	
	
	//Cria pastas dos Apps 
	public void insertFiles(String names[], String mainPath) {
		try {
			for(int i = 0; i < names.length; i++) {
				File creatFile = new File(mainPath + "\\" + names[i]);
				creatFile.mkdir();
			}
		}catch(Exception e) {
			System.out.print("Erro ao Criar Pastas");
		}
	}
	
	
	//Substitui pastas de imagem em /res
	public boolean substituiPastasImagens(File srcDirCustom, File dstDirCustom) {
		try{
            if( srcDirCustom.isDirectory() ){     		 
                if( !dstDirCustom.exists() ){
                	dstDirCustom.mkdir();
                }
                String[] children = srcDirCustom.list();
                for (int i=0; i<children.length; i++){
                	substituiPastasImagens( new File( srcDirCustom, children[i] ), new File( dstDirCustom, children[i] ) );
                }
        	}
         
        else{
            InputStream in = new FileInputStream( srcDirCustom );
            OutputStream out = new FileOutputStream( dstDirCustom );
            byte[] buf = new byte[1024];
            int len;
            while( (len = in.read( buf ) ) > 0 ) {
                out.write( buf, 0, len );
            }
            in.close();
            out.close();
        }
    }
    catch( IOException ioex ){
        ioex.printStackTrace();
        return false;
    }
    return true;
	}
	
	
   
	public static void main (String args[]) {
		//cria parametros para funcionamento 
		CreatFile cria = new CreatFile();
		String mainPath = "C:\\Users\\Vesti\\Documents\\Atualizacao_teste\\"; //Pasta origem de onde serão customizados os novos apps para publicação
		String srcPath = "C:\\Users\\Vesti\\Documents\\Atualizacao07_07\\";
		String names[] = {"Papaya","Tropical","GroovyForever","Esmeral"}; //Nome das pastas/apps que serem criados/customizados
		File srcDir = new  File(mainPath + "\\master_compras"); //Pasta origem do master para copia de arquivos para pastas destino
		String customPath = "\\app\\src\\main\\res\\";
		String mipmap = "mipmap-";
		String resPaths[] = {mipmap+"hdpi",mipmap+"mdpi",mipmap+"xhdpi",mipmap+"xxhdpi"};

		
		cria.insertFiles(names,mainPath);
		
		for(int i = 0; i < names.length; i++) {
			File dstDir = new File(mainPath + "\\" + names[i]);
			cria.substituiPastasImagens(srcDir,dstDir);
		}
		
		for(int c = 0; c < names.length; c++) {
			for(int i = 0; i < resPaths.length; i++){
				File srcDirCustom = new File(srcPath + names[c] + customPath + resPaths[i]);
				File dstDirCustom = new File(mainPath + names[c] + customPath + resPaths[i]);
				cria.substituiPastasImagens(srcDirCustom,dstDirCustom);
			}
			File srcLogoHeader = new File(srcPath + names[c] + customPath + "\\drawable\\logo_header.png");
			File dstLogoHeader = new File(mainPath + names[c] + customPath + "\\drawable\\logo_header.png"); 
			cria.substituiPastasImagens(srcLogoHeader, dstLogoHeader);
			
			File srcVActionBar = new File(srcPath + names[c] + customPath + "\\layout\\vesti_action_bar_layout.xml");
			File dstVAActionBar = new File(mainPath + names[c] + customPath + "\\layout\\vesti_action_bar_layout.xml"); 
			cria.substituiPastasImagens(srcVActionBar, dstVAActionBar);
			
			File srcActivyLogin = new File(srcPath + names[c] + customPath + "\\layout\\activity_login.xml");
			File dstActivyLogin = new File(mainPath + names[c] + customPath + "\\layout\\activity_login.xml"); 
			cria.substituiPastasImagens(srcActivyLogin, dstActivyLogin);
			
			File srcFragCatalogue = new File(srcPath + names[c] + customPath + "\\layout\\fragment_catalago.xml");
			File dstFragCatalogue = new File(mainPath + names[c] + customPath + "\\layout\\fragment_catalago.xml"); 
			cria.substituiPastasImagens(srcFragCatalogue, dstFragCatalogue);
			
			File srcColors = new File(srcPath + names[c] + customPath + "\\values\\colors.xml");
			File dstColors = new File(mainPath + names[c] + customPath + "\\values\\colors.xml"); 
			cria.substituiPastasImagens(srcColors, dstColors);
			
			File srcStrings = new File(srcPath + names[c] + customPath + "\\values\\strings.xml");
			File dstStrings = new File(mainPath + names[c] + customPath + "\\values\\strings.xml"); 
			cria.substituiPastasImagens(srcStrings, dstStrings);
			
			File srcManifest = new File(srcPath + names[c] + "\\app\\src\\main\\AndroidManifest.xml");
			File dstManifest = new File(mainPath + names[c] + "\\app\\src\\main\\AndroidManifest.xml"); 
			cria.substituiPastasImagens(srcManifest, dstManifest);
			
			File srcGlobals = new File(srcPath + names[c] + "\\app\\src\\main\\java\\com\\meuvesti\\cliente\\utils\\Globals.java");
			File dstGlobals = new File(mainPath + names[c] + "\\app\\src\\main\\java\\com\\meuvesti\\cliente\\utils\\Globals.java"); 
			cria.substituiPastasImagens(srcGlobals, dstGlobals);
			
			
			
		
			
		}
		
		
		
	}
}
