package Modelo;

import java.applet.Applet;
import java.applet.AudioClip;


public class Audio {

	private AudioClip sndPterodatilo, sndCaindo, sndAcerto,sndErro,sndMusic;

	public Audio() {
		sndPterodatilo = Applet.newAudioClip(getClass().getResource("/aviao.wav"));
		sndCaindo = Applet.newAudioClip(getClass().getResource("/caindo.wav"));
		sndAcerto = Applet.newAudioClip(getClass().getResource("/acerto.wav"));
		sndErro = Applet.newAudioClip(getClass().getResource("/erro.wav"));
		sndMusic = Applet.newAudioClip(getClass().getResource("/music.wav"));	
	}

	public AudioClip getSndPterodatilo() {	return sndPterodatilo;}
	public AudioClip getSndCaindo() { 		return sndCaindo; }
	public AudioClip getSndAcerto() { 		return sndAcerto;}
	public AudioClip getSndErro() {  		return sndErro;}
	public AudioClip getSndMusic() { 		return sndMusic;}
	
	



}
