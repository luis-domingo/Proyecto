using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RandomBackground : MonoBehaviour {

	public Sprite[] Backgrounds; //this is an array of type sprite
	private float lengthx, lengthy, startposx, startposy;
	public float parallaxEf;
	public GameObject cam;
	public SpriteRenderer Render; //this is a variable of type sprite renderer

	// Use this for initialization
	void Start () {
		Render = GetComponent<SpriteRenderer>();
		startposx=transform.position.x;
		startposy=transform.position.y;
		/*assigning the Render to the object's SpriteRender, this will allow us to access the image from 
            code*/
		lengthx = GetComponent<SpriteRenderer>().bounds.size.x;
		lengthy = GetComponent<SpriteRenderer>().bounds.size.y;
		Render.sprite = Backgrounds[Random.Range(0, Backgrounds.Length)]; 
		/*this will change the current sprite of the sprite renderer to a random sprite that was chosen 
            randomly from the array of backgrounds */
	}
	// Update is called once per frame
	void FixedUpdate () {
		float distx = (cam.transform.position.x*parallaxEf);
		float disty = (cam.transform.position.y*parallaxEf);
		transform.position= new Vector3(startposx + distx, startposy + disty, transform.position.z);
	}
}