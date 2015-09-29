package samples.docxandvelocity;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import samples.docxandvelocity.model.Project;
import samples.docxandvelocity.model.ProjectWithImage;

import java.io.*;

/**
 * Samples which uses the {@link ProjectWithImage} which contains {@link InputStream} image instead of
 * {@link IImageProvider}.
 */
public class DocxProjectWithVelocityAndImageWithoutImageProvider
{

    public static void main( String[] args )
    {
        try
        {
            // 1) Load Docx file by filling Velocity template engine and cache
            // it to the registry
            InputStream in = DocxProjectWithVelocityAndImageWithoutImageProvider.class.getResourceAsStream( "DocxProjectWithVelocityAndImageWithoutImageProvider.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport( in, TemplateEngineKind.Velocity );

            // 2) Create fields metadata to manage image
            FieldsMetadata metadata = report.createFieldsMetadata();
            // Image from InputStream (can works too with byte[])
            // Old API
            /*
             * metadata.addFieldAsImage( "logo", "project.logo" );
             * metadata.addFieldAsImage( "imageNotExistsAndRemoveImageTemplate", "project.nullLogo",
                                      NullImageBehaviour.RemoveImageTemplate );
            
            metadata.addFieldAsImage( "imageNotExistsAndKeepImageTemplate", "project.nullLogo",
                                      NullImageBehaviour.KeepImageTemplate );
            // Image from File
            metadata.addFieldAsImage( "logoFile", "project.logoFile" );
            
            metadata.addFieldAsImage( "fileImageNotExistsAndRemoveImageTemplate", "project.nullLogoFile",
                                      NullImageBehaviour.RemoveImageTemplate );
            
            metadata.addFieldAsImage( "fileImageNotExistsAndKeepImageTemplate", "project.nullLogoFile",
                                      NullImageBehaviour.KeepImageTemplate );
             */
            // NEW API which use @FieldMetadata
            metadata.load( "project", ProjectWithImage.class);

            // 3) Create context Java samples.docxandvelocity.model
            IContext context = report.createContext();
            Project project = new ProjectWithImage( "XDocReport" );
            context.put( "project", project );

            // 4) Generate report by merging Java samples.docxandvelocity.model with the Docx
            OutputStream out = new FileOutputStream( new File( "DocxProjectWithVelocityAndImageWithoutImageProvider_Out.docx" ) );

            report.process( context, out );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( XDocReportException e )
        {
            e.printStackTrace();
        }
    }
}
