using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameManager : MonoBehaviour {

	public static GameManager instance = null;
	public BoardManager boardScript;
	public int playerHealth = 70;
	public int playerCrystals =0;
	public float turnDelay = .1f;
	public float levelStartDelay = 5f;

	[HideInInspector]
	public bool playersTurn = true;

	private int level = 1;
	private bool doingSetup;
	//private Text levelText;
	//private GameObject levelImage;

	// Use this for initialization
	void Awake () {
		if (instance == null){
			instance = this;
		} else if (instance != this) {
			Destroy(gameObject);
		}

		DontDestroyOnLoad(gameObject);
		boardScript = GetComponent<BoardManager>();

		InitGame();
	}

	private void OnLevelWasLoaded(int index)
	{
		level++;
		InitGame();
	}

	private void HideLevelImage() {        
		//levelImage.SetActive(false);
		doingSetup = false;
	}

	void InitGame() {
		doingSetup = true;

//		levelImage = GameObject.Find("LevelImage");
//		levelText = GameObject.Find("LevelText").GetComponent<Text>();
//		levelText.text = "Level: " + level;        
//		levelImage.SetActive(true);        
//		Invoke("HideLevelImage", levelStartDelay);
//
//
		boardScript.BoardSetup(level);
	}

	public void GameOver() {
		enabled = false;
		//levelText.text = "You lost at level: " + level ;
		//levelImage.SetActive(true);
	}

	// Update is called once per frame
	void Update () {
		if(playersTurn || doingSetup) {
			return;
		}
	}




}