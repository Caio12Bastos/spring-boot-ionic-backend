package com.brq.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.brq.cursomc.services.exception.FileException;

@Service
public class ImagemService {

	public BufferedImage getJpgImagemArquivo(MultipartFile multipartFile) {
		String extencao = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		
		if(!"png".equals(extencao) && !"jpg".equals(extencao)) {
			throw new FileException("Somente imagens PNG e JPG são permitidos");
		}
		
		try {
			BufferedImage imagem = ImageIO.read(multipartFile.getInputStream());
			if("png".equals(extencao)) {
				imagem = pngToJpg(imagem);
			}
			return imagem;
		} catch(IOException excecao) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}

	public BufferedImage pngToJpg(BufferedImage imagem) {
		
		BufferedImage jpgImagem = new BufferedImage(imagem.getWidth(), imagem.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImagem.createGraphics().drawImage(imagem, 0, 0, Color.WHITE, null);
		
		return jpgImagem;
	}
	
	public InputStream getInputStream(BufferedImage imagem, String extencao) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(imagem, extencao, byteArrayOutputStream);
			
			return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		} catch(IOException excecao) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}
	
	public BufferedImage cortarQuadrado(BufferedImage imagem) {
		int minimo = (imagem.getHeight() <= imagem.getWidth()) ? imagem.getHeight() : imagem.getWidth();
		
		return Scalr.crop(imagem,
				(imagem.getWidth()/2) - (minimo/2), 
				(imagem.getHeight()/2) - (minimo/2), 
				minimo, 
				minimo);
	}
	
	public BufferedImage redimensionar(BufferedImage imagem, int size) {
		return Scalr.resize(imagem, Scalr.Method.ULTRA_QUALITY, size);
	}
	
}
